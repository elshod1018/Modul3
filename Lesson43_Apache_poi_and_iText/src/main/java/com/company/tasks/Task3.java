package com.company.tasks;

import com.company.db.Database;
import com.company.entity.Student;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {
        // student list ni pdfga jadval shaklida yozish
        List<Student> studentList = Database.getStudents();
        File file=new File(Database.BASE_DOCS_FOLDER,"students.pdf");
        file.getParentFile().mkdirs();
        try (PdfWriter writer = new PdfWriter(file);
             PdfDocument pdfDocument=new PdfDocument(writer);
             Document document=new Document(pdfDocument)) {

            Paragraph paragraph=new Paragraph("Students");
            paragraph.setTextAlignment(TextAlignment.CENTER);
            document.add(paragraph);

            float[] floats={20,15,20,15,40};
            Table table=new Table(floats);
            table.addCell("Name");
            table.addCell("Age");
            table.addCell("Region");
            table.addCell("Course");
            table.addCell("Languages");

            for (Student student : studentList) {
                table.addCell(student.getFullName());
                table.addCell(String.valueOf(student.getAge()));
                table.addCell(student.getRegion());
                table.addCell(String.valueOf(student.getCourse()));
                table.addCell(String.valueOf(student.getLanguages()));
            }
            document.add(table);

        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
