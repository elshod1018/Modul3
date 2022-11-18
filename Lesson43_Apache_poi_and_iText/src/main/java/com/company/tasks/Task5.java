package com.company.tasks;

import com.company.db.Database;
import com.company.entity.Student;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Task5 {
    public static void main(String[] args) {
        List<Student> studentList = Database.getStudents();
        File file=new File(Database.BASE_DOCS_FOLDER,"courseSortedStudents.xlsx");
        // student list ni excelga jadval shaklida yozish
        // har bir kurs talabalarini alohida alohida sheet ga yozish
        Set<Integer> courseSet=new TreeSet<>();
        for (Student student : studentList) {
            courseSet.add(student.getCourse());
        }
        try ( XSSFWorkbook workbook=new XSSFWorkbook();
              FileOutputStream outputStream = new FileOutputStream(file)) {
            for (Integer course : courseSet) {
                XSSFSheet sheet = workbook.createSheet(String.valueOf(course));
                XSSFRow row = sheet.createRow(0);
                row.createCell(0).setCellValue("Name");
                row.createCell(1).setCellValue("Age");
                row.createCell(2).setCellValue("Region");
                row.createCell(3).setCellValue("Course");
                row.createCell(4).setCellValue("Languages");

                int rowCounter=0;
                for (Student student : studentList) {
                    if (student.getCourse()==course){
                        row= sheet.createRow(++rowCounter);
                        row.createCell(0).setCellValue(student.getFullName());
                        row.createCell(1).setCellValue(student.getAge());
                        row.createCell(2).setCellValue(student.getRegion());
                        row.createCell(3).setCellValue(student.getCourse());
                        row.createCell(4).setCellValue(String.valueOf(student.getLanguages()));
                    }
                }
                for (int i = 0; i < 5; i++) {
                    sheet.autoSizeColumn(i);
                }
            }
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
