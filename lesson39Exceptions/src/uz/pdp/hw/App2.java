package uz.pdp.hw;

import uz.pdp.hw.Student;

import java.util.*;
import java.util.stream.Collectors;

public class App2 {
    static List<Student> studentList = new ArrayList<>();

    static {
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

            if(!student.getLanguages().contains(language)){
                student.getLanguages().add(language);
            }
        }
    }


    public static void main(String[] args) {

        // student list ni course bo'yicha guruhlash
        studentList.stream()
                .collect(Collectors.groupingBy(Student::getCourse))
                .forEach((key, value) -> System.out.println(studentList.get(key)));

    }
}
