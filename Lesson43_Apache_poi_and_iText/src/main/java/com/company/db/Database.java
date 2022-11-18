package com.company.db;

import com.company.entity.Car;
import com.company.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public interface Database {
    String BASE_IMAGES_FOLDER = "src/main/resources/images";
    String BASE_FOLDER = "src/main/resources";
    String BASE_DOCS_FOLDER = "src/main/resources/docs";
    String BASE_JSON_FOLDER = "src/main/resources/jsons";

    static List<Car> getCars() {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car(UUID.randomUUID(), "Malibu", "01A001AA", 10,
                BASE_IMAGES_FOLDER + "/" + "malibu.jpg"));
        cars.add(new Car(UUID.randomUUID(), "Malibu Turbo", "01A002AA", 10,
                BASE_IMAGES_FOLDER + "/" + "malibu_turbo.jpg"));
        cars.add(new Car(UUID.randomUUID(), "Cobalt", "01A003AA", 10,
                BASE_IMAGES_FOLDER + "/" + "cobalt.jpg"));
        cars.add(new Car(UUID.randomUUID(), "Lacetti", "01A004AA", 10,
                BASE_IMAGES_FOLDER + "/" + "lacetti.jpg"));
        cars.add(new Car(UUID.randomUUID(), "Gentra", "01A005AA", 10,
                BASE_IMAGES_FOLDER + "/" + "gentra.jpg"));
        cars.add(new Car(UUID.randomUUID(), "Spark", "01A006AA", 10,
                BASE_IMAGES_FOLDER + "/" + "spark.jpg"));
        cars.add(new Car(UUID.randomUUID(), "Best matiz", "01A007AA", 10,
                BASE_IMAGES_FOLDER + "/" + "best.jpg"));
        cars.add(new Car(UUID.randomUUID(), "Tesla", "01A008AA", 1,
                BASE_IMAGES_FOLDER + "/" + "tesla.jpg"));

        return cars;
    }

    static double getTotalPrice() {
        return getCars().stream()
                .map(Car::getPrice)
                .reduce(0d, Double::sum);
    }

    static List<Student> getStudents() {
        List<Student> studentList = new ArrayList<>();

        studentList.add(new Student("Shoxrux", 18, "Navoiy"));
        studentList.add(new Student("Obid", 19, "Jizzax", 3));
        studentList.add(new Student("Mirabbos", 22, "Fergana"));
        studentList.add(new Student("O'ktam", 18, "Fergana", 2));
        studentList.add(new Student("Abbos", 20, "Andijan", 3));
        studentList.add(new Student("Sarvar", 21, "Fergana", 4));
        studentList.add(new Student("Muhammad", 18, "Navoiy", 2));

        List<String> list = List.of("english", "uzbek", "russian",
                "turkish", "arabic", "spanish");

        for (int i = 0; i < 20; i++) {
            int studentIndex = new Random().nextInt(studentList.size());
            int languageIndex = new Random().nextInt(list.size());

            Student student = studentList.get(studentIndex);
            String language = list.get(languageIndex);

            if (!student.getLanguages().contains(language)) {
                student.getLanguages().add(language);
            }
        }

        return studentList;
    }
}
