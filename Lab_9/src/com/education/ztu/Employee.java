package com.education.ztu;

@CustomAnnotation(author = "HR", version = 1.0)
public class Employee {

    private String name;

    @CustomAnnotation(author = "Accounting", version = 1.1)
    public int monthlySalary;

    public Employee() {
        this.name = "Новий співробітник";
        this.monthlySalary = 20000;
    }

    public Employee(String name, int monthlySalary) {
        this.name = name;
        this.monthlySalary = monthlySalary;
    }

    @CustomAnnotation(author = "Аналітик", version = 2.0)
    public int calculateAnnualSalary() {
        return monthlySalary * 12;
    }

    private void setName(String newName) {
        this.name = newName;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', monthlySalary=" + monthlySalary + "}";
    }
}