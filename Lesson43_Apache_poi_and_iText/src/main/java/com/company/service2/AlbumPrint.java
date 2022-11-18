package com.company.service2;

import com.company.db.Database2;
import com.company.entity2.Album;
import com.itextpdf.kernel.pdf.PdfDocument;
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

public class AlbumPrint {
    static List<Album> albums = Database2.getAlbums();

    public static void main(String[] args) {
        printExcel();
//        printWord();
//        printPdf();
    }

    public static void printWord() {
        File file = new File(Database2.MAIN_DOCS, "album.docx");


        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun run = paragraph.createRun();
            run.setFontSize(16);
            run.setText("Albums");
            run.setBold(true);

            XWPFTable table = document.createTable();
            table.setWidth("80%");

            XWPFTableRow row = table.getRow(0);
            XWPFTableCell cell = row.getCell(0);
            cell.setWidth("15%");
            cell.setText("Id");

            cell = row.createCell();
            cell.setWidth("15%");
            cell.setText("UserId");

            cell = row.createCell();
            cell.setWidth("70%");
            cell.setText("Title");

            for (Album album : albums) {
                row = table.createRow();
                row.getCell(0).setText(String.valueOf(album.getId()));
                row.getCell(1).setText(String.valueOf(album.getUserId()));
                row.getCell(2).setText(album.getTitle());
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
        File file = new File(Database2.MAIN_EXCELS, "album.xlsx");

        try (FileOutputStream outputStream = new FileOutputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook()) {

            XSSFSheet sheet = workbook.createSheet();
            XSSFRow row = sheet.createRow(0);
            row.createCell(0).setCellValue("Id");
            row.createCell(1).setCellValue("UserId");
            row.createCell(2).setCellValue("Title");

            int rowCounter=0;
            for (Album album : albums) {
                row=sheet.createRow(++rowCounter);
                row.createCell(0).setCellValue(album.getId());
                row.createCell(1).setCellValue(album.getUserId());
                row.createCell(2).setCellValue(album.getTitle());
            }
            for (int i=0;i<3;i++){
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
        File file = new File(Database2.MAIN_PDFS, "album.pdf");

        try (PdfWriter writer = new PdfWriter(file);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            Paragraph paragraph = new Paragraph("Albums");
            paragraph.setTextAlignment(TextAlignment.CENTER);
            document.add(paragraph);

            float[] columns = {10, 10, 35};
            Table table = new Table(columns);
            for (Album album : albums) {
                table.addCell(String.valueOf(album.getId()));
                table.addCell(String.valueOf(album.getUserId()));
                table.addCell(album.getTitle());
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
