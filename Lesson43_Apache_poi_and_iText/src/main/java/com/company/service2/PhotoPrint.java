package com.company.service2;

import com.company.db.Database2;
import com.company.entity2.Photo;
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

public class PhotoPrint {
    static List<Photo> photos = Database2.getPhoto();

    public static void main(String[] args) {
//        printWord();
        printExcel();
//    printPdf();
    }

    public static void printWord() {
        File file = new File(Database2.MAIN_DOCS, "photo.docx");
        file.getParentFile().mkdirs();
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setFontSize(16);
            run.setText("Photos");
            run.setCapitalized(true);

            XWPFTable table = document.createTable();
            table.setWidth("100%");

            XWPFTableRow row = table.getRow(0);
            XWPFTableCell cell = row.getCell(0);
            cell.setWidth("15%");
            cell.setText("Id");

            cell = row.createCell();
            cell.setWidth("20%");
            cell.setText("AlbumId");

            cell = row.createCell();
            cell.setWidth("20%");
            cell.setText("Title");

            cell = row.createCell();
            cell.setWidth("20%");
            cell.setText("Url");

            cell = row.createCell();
            cell.setWidth("20%");
            cell.setText("Thumbnail");

            for (Photo photo:photos) {
                row = table.createRow();
                row.getCell(0).setText(String.valueOf(photo.getId()));
                row.getCell(1).setText(String.valueOf(photo.getAlbumId()));
                row.getCell(2).setText(photo.getTitle());
                row.getCell(3).setText(photo.getUrl());
                row.getCell(4).setText(photo.getThumbnailUrl());
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
        File file = new File(Database2.MAIN_EXCELS, "photo.xlsx");

        try (FileOutputStream outputStream = new FileOutputStream(file);
             XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Users");

            XSSFRow row = sheet.createRow(0);

            XSSFCell cell = row.createCell(0);
            cell.setCellValue("Id");
            cell = row.createCell(1);
            cell.setCellValue("AlbumId");
            cell = row.createCell(2);
            cell.setCellValue("Title");
            cell = row.createCell(3);
            cell.setCellValue("Url");
            cell = row.createCell(4);
            cell.setCellValue("ThumbnailUrl");

            int rowCounter = 0;
            for (Photo photo : photos) {
                row = sheet.createRow(++rowCounter);
                row.createCell(0).setCellValue(photo.getId());
                row.createCell(1).setCellValue(photo.getAlbumId());
                row.createCell(2).setCellValue(photo.getTitle());
                row.createCell(3).setCellValue(photo.getUrl());
                row.createCell(4).setCellValue(photo.getThumbnailUrl());
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
        File file = new File(Database2.MAIN_PDFS, "photo.pdf");

        try (PdfWriter pdfWriter = new PdfWriter(file);
             PdfDocument pdfDocument = new PdfDocument(pdfWriter);
             Document document = new Document(pdfDocument);) {

            Paragraph paragraph=new Paragraph("Users");
            paragraph.setTextAlignment(TextAlignment.CENTER);
            document.add(paragraph);

            float []columns={10,20,20,20,20};
            Table table=new Table(columns);
            table.addCell("Id");
            table.addCell("AlbumId");
            table.addCell("Title");
            table.addCell("Url");
            table.addCell("ThumbnailUrl");

            for (Photo photo : photos) {
                table.addCell(String.valueOf(photo.getId()));
                table.addCell(String.valueOf(photo.getAlbumId()));
                table.addCell(photo.getTitle());
                table.addCell(photo.getUrl());
                table.addCell(photo.getThumbnailUrl());
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
