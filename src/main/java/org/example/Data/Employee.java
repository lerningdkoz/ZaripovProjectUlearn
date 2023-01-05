package org.example.Data;

public class Employee {
    private Integer id;
    private Integer district;
    private String school;
    private String country;
    private String grades;
    private Integer students;
    private Double teacher;
    private Double calWorks;
    private Double lunch;
    private Integer computer;
    private Double expenditure;
    private Double income;
    private Double english;
    private Double read;
    private Double math;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDistrict() {
        return district;
    }

    public void setDistrict(Integer district) {
        this.district = district;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGrades() {
        return grades;
    }

    public void setGrades(String grades) {
        this.grades = grades;
    }

    public Integer getStudents() {
        return students;
    }

    public void setStudents(Integer students) {
        this.students = students;
    }

    public Double getTeacher() {
        return teacher;
    }

    public void setTeacher(Double teacher) {
        this.teacher = teacher;
    }

    public Double getCalWorks() {
        return calWorks;
    }

    public void setCalWorks(Double calWorks) {
        this.calWorks = calWorks;
    }

    public Double getLunch() {
        return lunch;
    }

    public void setLunch(Double lunch) {
        this.lunch = lunch;
    }

    public Integer getComputer() {
        return computer;
    }

    public void setComputer(Integer computer) {
        this.computer = computer;
    }

    public Double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(Double expenditure) {
        this.expenditure = expenditure;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getEnglish() {
        return english;
    }

    public void setEnglish(Double english) {
        this.english = english;
    }

    public Double getRead() {
        return read;
    }

    public void setRead(Double read) {
        this.read = read;
    }

    public Double getMath() {
        return math;
    }

    public void setMath(Double math) {
        this.math = math;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", district=" + district +
                        ", school='" + school + '\'' +
                        ", country='" + country + '\'' +
                        ", grades='" + grades + '\'' +
                        ", studentId=" + students +
                        ", teacher=" + teacher +
                        ", calWorks=" + calWorks +
                        ", lunch=" + lunch +
                        ", computer=" + computer +
                        ", expenditure=" + expenditure +
                        ", income=" + income +
                        ", english=" + english +
                        ", read=" + read +
                        ", math=" + math;
    }
}
