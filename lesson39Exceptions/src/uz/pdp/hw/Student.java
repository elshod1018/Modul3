package uz.pdp.hw;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String fullName;
    private int age;
    private String region;
    private int course = 1;
    private List<String> languages = new ArrayList<>();

    public Student(String fullName, int age, String region) {
        this.fullName = fullName;
        this.age = age;
        this.region = region;
    }

    public Student(String fullName, int age, String region, int course) {
        this.fullName = fullName;
        this.age = age;
        this.region = region;
        this.course = course;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getRegion() {
        return region;
    }

    public int getCourse() {
        return course;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<String> getLanguages() {
        return languages;
    }

    @Override
    public String toString() {
        return "Student{" +
                "fullName='" + fullName + '\'' +
                ", age=" + age +
                ", region='" + region + '\'' +
                ", course=" + course +
                ", languages=" + languages +
                '}';
    }
}
