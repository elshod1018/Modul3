package com.company.service2;

import com.company.db.Database2;
import com.company.entity2.User;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class UserPrint {
    static List<User> users = Database2.getUsers();

    public static void main(String[] args) {
//        printWord();
//        printExcel();
//    printPdf();
    }

    public static void printWord() {
        File file = new File(Database2.MAIN_DOCS, "users.docx");
        file.getParentFile().mkdirs();
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setFontSize(16);
            run.setText("Users");
            run.setCapitalized(true);

            XWPFTable table = document.createTable();
            table.setWidth("100%");

            XWPFTableRow row = table.getRow(0);
            XWPFTableCell cell = row.getCell(0);
            cell.setWidth("15%");
            cell.setText("Id");

            cell = row.createCell();
            cell.setWidth("20%");
            cell.setText("Name");

            cell = row.createCell();
            cell.setWidth("20%");
            cell.setText("Phone");

            cell = row.createCell();
            cell.setWidth("20%");
            cell.setText("Email");

            cell = row.createCell();
            cell.setWidth("20%");
            cell.setText("Username");

            for (User user : users) {
                row = table.createRow();
                row.getCell(0).setText(String.valueOf(user.getId()));
                row.getCell(1).setText(user.getName());
                row.getCell(2).setText(user.getPhone());
                row.getCell(3).setText(user.getEmail());
                row.getCell(4).setText(user.getUsername());
            }
            document.write(outputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printExcel() {
        File file = new File(Database2.MAIN_EXCELS, "user.xlsx");

        try (FileOutputStream outputStream = new FileOutputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Users");

            XSSFRow row = sheet.createRow(0);

            XSSFCell cell = row.createCell(0);
            cell.setCellValue("Id");
            cell = row.createCell(1);
            cell.setCellValue("Name");
            cell = row.createCell(2);
            cell.setCellValue("Phone");
            cell = row.createCell(3);
            cell.setCellValue("Email");
            cell = row.createCell(4);
            cell.setCellValue("User name");

            int rowCounter = 0;
            for (User user : users) {
                row = sheet.createRow(++rowCounter);
                row.createCell(0).setCellValue(user.getId());
                row.createCell(1).setCellValue(user.getName());
                row.createCell(2).setCellValue(user.getPhone());
                row.createCell(3).setCellValue(user.getEmail());
                row.createCell(4).setCellValue(user.getUsername());
            }
            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printPdf() {
        File file = new File(Database2.MAIN_PDFS, "user.pdf");

        try (PdfWriter pdfWriter = new PdfWriter(file);
             PdfDocument pdfDocument = new PdfDocument(pdfWriter);
             Document document = new Document(pdfDocument);) {

            Paragraph paragraph=new Paragraph("Users");
            paragraph.setTextAlignment(TextAlignment.CENTER);
            document.add(paragraph);

            float []columns={10,20,20,20,20};
            Table table=new Table(columns);
            table.addCell("Id");
            table.addCell("Name");
            table.addCell("Phone");
            table.addCell("Email");
            table.addCell("User name");

            for (User user : users) {
                table.addCell(String.valueOf(user.getId()));
                table.addCell(user.getName());
                table.addCell(user.getPhone());
                table.addCell(user.getEmail());
                table.addCell(user.getUsername());
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
