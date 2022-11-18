package com.company.service2;

import com.company.db.Database2;
import com.company.entity2.Post;
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

public class PostPrint {
    static List<Post> posts = Database2.getPosts();

    public static void main(String[] args) {
//        printWord();
        printExcel();
//    printPdf();
    }

    public static void printWord() {
        File file = new File(Database2.MAIN_DOCS, "posts.docx");
        file.getParentFile().mkdirs();
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setFontSize(16);
            run.setText("Posts");
            run.setCapitalized(true);

            XWPFTable table = document.createTable();
            table.setWidth("100%");

            XWPFTableRow row = table.getRow(0);
            XWPFTableCell cell = row.getCell(0);
            cell.setWidth("15%");
            cell.setText("Id");

            cell = row.createCell();
            cell.setWidth("15%");
            cell.setText("UserId");

            cell = row.createCell();
            cell.setWidth("30%");
            cell.setText("Title");

            cell = row.createCell();
            cell.setWidth("40%");
            cell.setText("Body");

            for (Post post : posts) {
                row = table.createRow();
                row.getCell(0).setText(String.valueOf(post.getId()));
                row.getCell(1).setText(String.valueOf(post.getUserId()));
                row.getCell(2).setText(post.getTitle());
                row.getCell(3).setText(post.getBody());
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
        File file = new File(Database2.MAIN_EXCELS, "post.xlsx");

        try (FileOutputStream outputStream = new FileOutputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Posts");

            XSSFRow row = sheet.createRow(0);

            XSSFCell cell = row.createCell(0);
            cell.setCellValue("Id");
            cell = row.createCell(1);
            cell.setCellValue("UserId");
            cell = row.createCell(2);
            cell.setCellValue("Title");
            cell = row.createCell(3);
            cell.setCellValue("Body");

            int rowCounter = 0;
            for (Post post : posts) {
                row = sheet.createRow(++rowCounter);
                row.createCell(0).setCellValue(post.getId());
                row.createCell(1).setCellValue(post.getUserId());
                row.createCell(2).setCellValue(post.getTitle());
                row.createCell(3).setCellValue(post.getBody());
            }
            for (int i = 0; i < 4; i++) {
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
        File file = new File(Database2.MAIN_PDFS, "post.pdf");

        try (PdfWriter pdfWriter = new PdfWriter(file);
             PdfDocument pdfDocument = new PdfDocument(pdfWriter);
             Document document = new Document(pdfDocument);) {

            Paragraph paragraph = new Paragraph("Posts");
            paragraph.setTextAlignment(TextAlignment.CENTER);
            document.add(paragraph);

            float[] columns = {10, 20, 20, 20, 20};
            Table table = new Table(columns);
            table.addCell("Id");
            table.addCell("UserId");
            table.addCell("Title");
            table.addCell("Body");

            for (Post post : posts) {
                table.addCell(String.valueOf(post.getId()));
                table.addCell(String.valueOf(post.getUserId()));
                table.addCell(post.getTitle());
                table.addCell(post.getBody());
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
