package com.company.tasks;

import com.company.db.Database;
import com.company.entity.Student;
import org.apache.poi.xwpf.usermodel.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
// student list ni wordga jadval shaklida yozish
public class Task1 {
    public static void main(String[] args) {
        List<Student> studentList = Database.getStudents();
        File file = new File(Database.BASE_DOCS_FOLDER, "students.docx");
        file.getParentFile().mkdirs();

        try (XWPFDocument document=new XWPFDocument();
             FileOutputStream outputStream = new FileOutputStream(file)) {
            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setFontFamily("Consolas");
            run.setFontSize(16);
            run.setText("Students");

            XWPFTable table=document.createTable();
            table.setWidth("100%");
            XWPFTableRow row = table.getRow(0);

            XWPFTableCell cell = row.getCell(0);
            cell.setWidth("20%");
            cell.setText("Full name");

            cell=row.createCell();
            cell.setWidth("10%");
            cell.setText("Age");

            cell=row.createCell();
            cell.setWidth("20%");
            cell.setText("Region");

            cell=row.createCell();
            cell.setWidth("20%");
            cell.setText("Course");

            cell=row.createCell();
            cell.setWidth("30%");
            cell.setText("Language");

            for (Student student : studentList) {
                row=table.createRow();
                row.getCell(0).setText(student.getFullName());
                row.getCell(1).setText(String.valueOf(student.getAge()));
                row.getCell(2).setText(student.getRegion());
                row.getCell(3).setText(String.valueOf(student.getCourse()));
                row.getCell(4).setText(String.valueOf(student.getLanguages()));
            }
            document.write(outputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
