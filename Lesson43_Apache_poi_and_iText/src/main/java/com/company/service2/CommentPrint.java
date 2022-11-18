package com.company.service2;

import com.company.db.Database2;
import com.company.entity2.Comment;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.*;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class CommentPrint {
    static List<Comment> comments = Database2.getComments();

    public static void main(String[] args) {
//        printWord();
//        printPdf();
//        printExcel();
    }

    public static void printWord() {
        File file = new File(Database2.MAIN_DOCS, "comment.docx");
        file.getParentFile().mkdirs();

        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun run = paragraph.createRun();
            run.setCapitalized(true);
            run.setText("Comments");
            run.setFontSize(16);
            run.setBold(true);

            XWPFTable table = document.createTable();
            table.setWidth("100%");

            XWPFTableRow row = table.getRow(0);
            XWPFTableCell cell = row.getCell(0);
            cell.setWidth("10%");
            cell.setText("Id");

            cell = row.createCell();
            cell.setWidth("10%");
            cell.setText("PostId");

            cell = row.createCell();
            cell.setWidth("25%");
            cell.setText("Name");

            cell = row.createCell();
            cell.setWidth("35%");
            cell.setText("Body");

            cell = row.createCell();
            cell.setWidth("20%");
            cell.setText("Email");

            for (Comment comment : comments) {
                row = table.createRow();
                row.getCell(0).setText(String.valueOf(comment.getId()));
                row.getCell(1).setText(String.valueOf(comment.getPostId()));
                row.getCell(2).setText(comment.getName());
                row.getCell(3).setText(comment.getBody());
                row.getCell(4).setText(comment.getEmail());
            }

            document.write(outputStream);

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
        File file = new File(Database2.MAIN_PDFS, "comment.pdf");
        file.getParentFile().mkdirs();

        try (PdfWriter writer = new PdfWriter(file);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {
            Paragraph paragraph = new Paragraph("Comments");
            paragraph.setTextAlignment(TextAlignment.CENTER);
            document.add(paragraph);

            float[] columns = {15, 10, 15, 35, 15};
            Table table = new Table(columns);
            table.addCell("Id");
            table.addCell("PostId");
            table.addCell("Name");
            table.addCell("Body");
            table.addCell("Email");
            for (Comment comment : comments) {
                table.addCell(String.valueOf(comment.getId()));
                table.addCell(String.valueOf(comment.getPostId()));
                table.addCell(comment.getName());
                table.addCell(comment.getBody());
                table.addCell(comment.getEmail());
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

    public static void printExcel() {
        File file=new File(Database2.MAIN_EXCELS,"comment.xlsx");


        try (FileOutputStream outputStream = new FileOutputStream(file);
             XSSFWorkbook workbook=new XSSFWorkbook()) {
            XSSFSheet sheet=workbook.createSheet("Comments");
            XSSFRow row=sheet.createRow(0);
            row.createCell(0).setCellValue("Id");
            row.createCell(1).setCellValue("PostId");
            row.createCell(2).setCellValue("Name");
            row.createCell(3).setCellValue("Body");
            row.createCell(4).setCellValue("Email");

            int rowCounter=0;
            for (Comment comment : comments) {
                row=sheet.createRow(++rowCounter);
                row.createCell(0).setCellValue(comment.getId());
                row.createCell(1).setCellValue(comment.getPostId());
                row.createCell(2).setCellValue(comment.getName());
                row.createCell(3).setCellValue(comment.getBody());
                row.createCell(4).setCellValue(comment.getEmail());
            }
            for (int i=0;i<5;i++){
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
