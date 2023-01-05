package org.example.Data;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.List;
import java.util.logging.Logger;
import java.sql.ResultSet;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;

public class EmployeeService {
    public final Logger logger = Logger.getLogger(EmployeeService.class.getName());
    private final Connection connection;

    public EmployeeService(Connection connection) {
        this.connection = connection;
    }

    private CategoryDataset createDataset() {
        Map<String, Double> map = new HashMap<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT country as 'country', AVG(students) as 'AvgStudents' " +
                    "FROM schoolTable " +
                    "GROUP BY country");
            int counter = 0;
            Random random = new Random();
            while (rs.next()) {
                if (counter == 10)
                    break;
                if (random.nextInt(2) == 1) {
                    counter++;
                    map.put(rs.getString("country"), rs.getDouble("AvgStudents"));
                }
            }
            statement.close();
        } catch (SQLException e) {
            logger.info("[ERROR]Graphics");
            System.exit(0);
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (var entry : map.entrySet()) {
            dataset.addValue(entry.getValue(), entry.getKey(), "Country name");
        }
        return dataset;
    }

    public JFreeChart createChart() {
        CategoryDataset dataset = createDataset();
        JFreeChart chart = ChartFactory.createBarChart(
                "Average number of students",
                "Country",
                "Average number of students in the country",
                dataset);
        chart.setBackgroundPaint(Color.white);

        CategoryPlot plot = (CategoryPlot) chart.getPlot();

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        chart.getLegend().setFrame(BlockBorder.NONE);

        return chart;
    }

    public void showGraphics() {
        var chart = createChart();
        JFrame frame =
                new JFrame("Graphics");
        frame.getContentPane()
                .add(new ChartPanel(chart));
        frame.setSize(1000, 800);
        frame.setVisible(true);
    }

    public void getAvg() {
        try {
            Statement statement = connection.createStatement();
            List<String> countries = new ArrayList<>(Arrays.asList("Fresno", "Contra Costa", "El Dorado", "Glenn"));
            logger.info("[DONE]Find avg!");
            Thread.sleep(1000);
            for (var country : countries) {
                ResultSet rs = statement.executeQuery("SELECT AVG(expenditure) AS getAvg FROM schoolTable " +
                        "WHERE country = '" + country + "' " +
                        "AND expenditure > 10");
                while (rs.next()) {
                    System.out.println("Avg " + country + ": " + rs.getDouble("getAvg"));
                }
            }
            statement.close();
        } catch (SQLException | InterruptedException e) {
            logger.info("[ERROR]Find avg!");
        }
    }

    public void getBetween() {
        double resultMath = Double.MIN_VALUE;
        String resultSchool = "";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT math, school FROM schoolTable " +
                    "WHERE (schoolTable.students BETWEEN 5000 AND 7500) " +
                    "OR (schoolTable.students BETWEEN 10000 AND 11000) ");
            while (resultSet.next()) {
                if (resultSet.getDouble("math") > resultMath) {
                    resultMath = resultSet.getDouble("math");
                    resultSchool = resultSet.getString("school");
                }
            }
            logger.info("[DONE]Find between!");
            resultSet.close();
            statement.close();
            Thread.sleep(1000);
            System.out.println("Result: school - " + resultSchool + ", math - " + resultMath);
        } catch (SQLException | InterruptedException e) {
            logger.info("[ERROR]Find between!");
        }
    }

    public List<Employee> getAllEmployeeList() {
        List<Employee> list = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM schoolTable");
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setId(rs.getInt("id"));
                employee.setDistrict(rs.getInt("district"));
                employee.setSchool(rs.getString("school"));
                employee.setCountry(rs.getString("country"));
                employee.setGrades(rs.getString("grades"));
                employee.setStudents(rs.getInt("students"));
                employee.setTeacher(rs.getDouble("teacher"));
                employee.setCalWorks(rs.getDouble("calWorks"));
                employee.setLunch(rs.getDouble("lunch"));
                employee.setComputer(rs.getInt("computer"));
                employee.setExpenditure(rs.getDouble("expenditure"));
                employee.setIncome(rs.getDouble("income"));
                employee.setEnglish(rs.getDouble("english"));
                employee.setRead(rs.getDouble("read"));
                employee.setMath(rs.getDouble("math"));
                list.add(employee);
            }
            rs.close();
            statement.close();
            logger.info("[DONE]Select values!");
            Thread.sleep(500);
        } catch (SQLException | InterruptedException e) {
            logger.info("[ERROR]Select values!");
        }
        return new ArrayList<>(list);
    }

    public void createTable() {
        String queryDrop = "DROP TABLE IF EXISTS schoolTable";
        String query =
                "CREATE TABLE IF NOT EXISTS schoolTable(\n" +
                        "id INT PRIMARY KEY,\n" +
                        "district INT NOT NULL,\n" +
                        "school VARCHAR(250) NOT NULL,\n" +
                        "country  VARCHAR(250) NOT NULL,\n" +
                        "grades VARCHAR(250) NOT NULL,\n" +
                        "students INT NOT NULL,\n" +
                        "teacher DOUBLE NOT NULL,\n" +
                        "calWorks DOUBLE NOT NULL,\n" +
                        "lunch DOUBLE NOT NULL,\n" +
                        "computer INT NOT NULL,\n" +
                        "expenditure DOUBLE NOT NULL,\n" +
                        "income DOUBLE NOT NULL,\n" +
                        "english DOUBLE NOT NULL,\n" +
                        "read DOUBLE NOT NULL,\n" +
                        "math DOUBLE NOT NULL\n" +
                        ");";
        try {
            Statement statement = connection.createStatement();
            statement.execute(queryDrop);
            statement.execute(query);
            logger.info("[DONE]Create table!");
            statement.close();
        } catch (SQLException e) {
            logger.info("[ERROR]Create table!");
            System.exit(0);
        }
    }

    private void insertValues(Employee el, Statement statement) {
        String query = "INSERT INTO schoolTable (id, district, school, country, grades," +
                " students, teacher, calWorks, lunch, computer, expenditure, income, english, read, math)" +
                " VALUES (" + el.getId() + ", " + el.getDistrict() + "," +
                " '" + el.getSchool() + "', '" + el.getCountry() + "'," +
                " '" + el.getGrades() + "', " + el.getStudents() + "," +
                " " + el.getTeacher() + ", " + el.getCalWorks() + "," +
                " " + el.getLunch() + ", " + el.getComputer() + "," +
                " " + el.getExpenditure() + "," +
                " " + el.getIncome() + "," +
                " " + el.getEnglish() + "," +
                " " + el.getRead() + "," +
                " " + el.getMath() + ")";
        try {
            statement.execute(query);
            statement.close();
        } catch (SQLException e) {
            logger.info("[ERROR]Insert values!");
            logger.info("[ERROR INSERT] " + el.toString());
            System.exit(0);
        }
    }

    private Employee getEmployee(String[] fragments) {
        Employee employee = new Employee();
        employee.setId(Integer.valueOf(fragments[0]));
        employee.setDistrict(Integer.valueOf(fragments[1]));
        employee.setSchool(fragments[2]);
        employee.setCountry(fragments[3]);
        employee.setGrades(fragments[4]);
        employee.setStudents(Integer.valueOf(fragments[5]));
        employee.setTeacher(Double.valueOf(fragments[6]));
        employee.setCalWorks(Double.valueOf(fragments[7]));
        employee.setLunch(Double.valueOf(fragments[8]));
        employee.setComputer(Integer.valueOf(fragments[9]));
        employee.setExpenditure(Double.valueOf(fragments[10]));
        employee.setIncome(Double.valueOf(fragments[11]));
        employee.setEnglish(Double.valueOf(fragments[12]));
        employee.setRead(Double.valueOf(fragments[13]));
        employee.setMath(Double.valueOf(fragments[14]));
        return employee;
    }

    public void setValuesInDataBase(String sPath) {
        Path path = Path.of(sPath);
        try {
            Statement statement = connection.createStatement();
            int checkValue = 0;
            List<String> lines = Files.readAllLines(path);
            for (var line : lines) {
                String[] fragments = line.replace("\"", "").split(",");
                if (checkValue != 0) {
                    insertValues(getEmployee(fragments), statement);
                }
                checkValue++;
            }
            logger.info("[DONE]Insert values!");
            statement.close();
        } catch (IOException | SQLException e) {
            logger.info("[ERROR]Insert value!");
            System.exit(0);
        }
    }

}
