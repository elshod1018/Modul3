package m2_2.m2_2_3;

public class Employee {
    private static long counter=0;
    {
        counter++;
        id=counter;
    }
    private final long id;
    private String name;
    private String sureName;
    private double salary;

    public Employee() {
    }

    public Employee(String name, String sureName, double salary) {
        this.name = name;
        this.sureName = sureName;
        this.salary = salary;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", sureName='" + sureName + '\'' +
                ", salary=" + salary +
                '}';
    }
}
