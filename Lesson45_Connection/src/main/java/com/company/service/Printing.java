package com.company.service;

import com.company.database.Database;
import com.company.entity.Currency;
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

public class Printing {
    static List<Currency> currencies = Database.getCurrency();

    public static void main(String[] args) {
        printToExcel();

    }

    public static void printToExcel() {
        File file = new File("src/main/resources/currencies.xlsx");
        file.getParentFile().mkdirs();


        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            XSSFSheet sheet = workbook.createSheet();
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell = row.createCell(0);
            cell.setCellValue("Id");
            row.createCell(1).setCellValue("Ccy");
            row.createCell(2).setCellValue("ccyNmUz");
            row.createCell(3).setCellValue("Rate");

            int rowCounter = 0;
            for (Currency currency : currencies) {
                row = sheet.createRow(++rowCounter);
                row.createCell(0).setCellValue(currency.getId());
                row.createCell(1).setCellValue(currency.getCcy());
                row.createCell(2).setCellValue(currency.getCcyNmUZ());
                row.createCell(3).setCellValue(currency.getRate());
            }
            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }
            workbook.write(outputStream);
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void printToWord(){
        File file=new File("src/main/resources/currencies.docx");


        try (XWPFDocument document=new XWPFDocument();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            XWPFParagraph paragraph=document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun run = paragraph.createRun();
            run.setText("Currency");
            run.setFontSize(16);
            run.setBold(true);

            XWPFTable table = document.createTable();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
