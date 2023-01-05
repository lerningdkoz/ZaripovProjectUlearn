package org.example;


import org.example.Data.EmployeeService;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;


public class Main {
    public final static Logger logger = Logger.getLogger(Main.class.getName());
    public static Connection connection;
    public static final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.print("Specify the absolute path to the data file: ");
        //Школы.csv
        String path = bufferedReader.readLine();

        openConnection();

        EmployeeService employeeService = new EmployeeService(connection);
        employeeService.createTable();
        employeeService.setValuesInDataBase(path);
        Thread.sleep(500);
        String choice;
        while (true) {
            System.out.println("1 - Show graph");
            System.out.println("2 - Show the average amount of expenses");
            System.out.println("3 - Show the school with the highest score in math");
            System.out.println("4 - Show part of the data");
            System.out.println("Any value - Exit");
            System.out.print("Your choice: ");
            choice = bufferedReader.readLine();
            switch (choice) {
                case "1" -> employeeService.showGraphics();
                case "2" -> employeeService.getAvg();
                case "3" -> employeeService.getBetween();
                case "4" -> {
                    try {
                        System.out.print("Enter the amount of data you want to see(1-420): ");
                        int count = Integer.parseInt(bufferedReader.readLine());
                        if (count < 1 || count > 420)
                            throw new NumberFormatException();
                        var listEmployee = employeeService.getAllEmployeeList();
                        for (int i = 0; i < count; i++) {
                            System.out.println(listEmployee.get(i).toString());
                        }
                    } catch (NumberFormatException | IOException e) {
                        logger.info("[ERROR]Invalid number");
                    }

                }
                default -> {
                    closeConnection();
                    System.exit(0);
                }
            }
            System.out.println();
        }
    }

    public static void openConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:school.db");
            logger.info("[DONE]Open connection!");
        } catch (ClassNotFoundException | SQLException e) {
            logger.info("[ERROR]Open Connection!");
            System.exit(0);
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
            logger.info("[DONE]Close connection!");
        } catch (SQLException e) {
            logger.info("[ERROR]Close connection!");
            System.exit(0);
        }
    }

}