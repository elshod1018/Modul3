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
import java.util.List;

public class SpareTask2 {
    public static void main(String[] args) {
        List<Student>studentList= Database.getStudents();
        File file=new File(Database.BASE_FOLDER,"spare2.xlsx");

        try (XSSFWorkbook workbook=new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(file)) {
            XSSFSheet sheet=workbook.createSheet("Students");

            XSSFRow row= sheet.createRow(0);
            row.createCell(0).setCellValue("Name");
            row.createCell(1).setCellValue("Age");
            row.createCell(1).setCellValue("Region");
            row.createCell(1).setCellValue("Course");
            row.createCell(1).setCellValue("Languages");

            int rowCounter=0;
            for (Student student : studentList) {
                row= sheet.createRow(++rowCounter);
                row.createCell(0).setCellValue(student.getFullName().toUpperCase());
                row.createCell(1).setCellValue(student.getAge());
                row.createCell(2).setCellValue(student.getRegion());
                row.createCell(3).setCellValue(student.getCourse());
                row.createCell(4).setCellValue(String.valueOf(student.getLanguages()));
            }

            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
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
