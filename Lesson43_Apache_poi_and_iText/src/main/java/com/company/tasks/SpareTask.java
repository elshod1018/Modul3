package com.company.tasks;

import com.company.db.Database;
import com.company.entity.Student;
import org.apache.poi.xwpf.usermodel.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpareTask {
    public static void main(String[] args) {
        List<Student>studentList= Database.getStudents();
        File file=new File(Database.BASE_FOLDER,"spare.docx");

        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream outputStream=new FileOutputStream(file)) {
            XWPFParagraph paragraph=document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun run=paragraph.createRun();
            run.setBold(true);
            run.setFontSize(16);
            run.setText("Students");

            XWPFTable table=document.createTable();
           table.setWidth("100%");


           document.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.open(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
