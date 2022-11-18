package com.company.service2;

import com.company.db.Database2;
import com.company.entity2.Todo;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class TodoPrint {
    static List<Todo> todos = Database2.getTodos();

    public static void main(String[] args) {
//        printWord();
//        printPdf();
//        printExcel();
    }

    public static void printWord() {
        File file = new File(Database2.MAIN_DOCS, "todo.docx");

        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setText("Todos");
            run.setFontSize(16);
            run.setCapitalized(true);

            XWPFTable table = document.createTable();
            table.setWidth("100%");

            XWPFTableRow row = table.getRow(0);
            XWPFTableCell cell = row.getCell(0);
            cell.setWidth("10%");
            cell.setText("Id");

            cell = row.createCell();
            cell.setWidth("10%");
            cell.setText("UserId");

            cell = row.createCell();
            cell.setWidth("15%");
            cell.setText("Completed");

            cell = row.createCell();
            cell.setWidth("65%");
            cell.setText("Title");

            for (Todo todo : todos) {
                row = table.createRow();
                row.getCell(0).setText(String.valueOf(todo.getId()));
                row.getCell(1).setText(String.valueOf(todo.getUserId()));
                row.getCell(2).setText(String.valueOf(todo.getCompleted()));
                row.getCell(3).setText(String.valueOf(todo.getTitle()));
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

    public static void printExcel() {
        File file = new File(Database2.MAIN_EXCELS, "todo.xlsx");

        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            XSSFSheet sheet = workbook.createSheet("Todos");
            XSSFRow row = sheet.createRow(0);

            row.createCell(0).setCellValue("Id");
            row.createCell(1).setCellValue("UserId");
            row.createCell(2).setCellValue("Completed");
            row.createCell(3).setCellValue("Title");

            int rowCounter = 0;
            for (Todo todo : todos) {
                row = sheet.createRow(++rowCounter);
                row.createCell(0).setCellValue(todo.getId());
                row.createCell(1).setCellValue(todo.getUserId());
                row.createCell(2).setCellValue(todo.getCompleted());
                row.createCell(3).setCellValue(todo.getTitle());
            }
            for (int i = 0; i < 4; i++) {
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

    public static void printPdf() {
        File file = new File(Database2.MAIN_PDFS, "todo.pdf");


        try (PdfWriter writer = new PdfWriter(file);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            Paragraph paragraph = new Paragraph("Todos");
            paragraph.setTextAlignment(TextAlignment.CENTER);
            document.add(paragraph);

            float[] columns = {10, 10, 15, 40};
            Table table = new Table(columns);
            table.addCell("Id");
            table.addCell("UserId");
            table.addCell("Completed");
            table.addCell("Title");

            for (Todo todo : todos) {
                table.addCell(String.valueOf(todo.getId()));
                table.addCell(String.valueOf(todo.getUserId()));
                table.addCell(String.valueOf(todo.getCompleted()));
                table.addCell(todo.getTitle());
            }
            document.add(table);

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
