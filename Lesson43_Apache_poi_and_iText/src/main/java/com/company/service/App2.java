package com.company.service;

import com.company.db.Database;
import com.company.entity.Car;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class App2 {

    public static void main(String[] args) {
        File file = new File(Database.BASE_DOCS_FOLDER, "cars.xlsx");
        file.getParentFile().mkdirs();

        try (XSSFWorkbook workbook = new XSSFWorkbook();
             FileOutputStream out = new FileOutputStream(file)) {

            XSSFSheet sheet = workbook.createSheet("cars");

            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Model");
            headerRow.createCell(1).setCellValue("Number");
            headerRow.createCell(2).setCellValue("Price");
            headerRow.createCell(3).setCellValue("Image");

            List<Car> carList = Database.getCars();

            int rowIndex = 0;
            for (Car car : carList) {
                XSSFRow row = sheet.createRow(++rowIndex);
                row.createCell(0).setCellValue(car.getModel());
                row.createCell(1).setCellValue(car.getNumber());
                row.createCell(2).setCellValue(car.getPrice());
                row.createCell(3).setCellValue(car.getImageUrl());
            }

            for (int i = 0; i < 4; i++) {
                sheet.autoSizeColumn(i);
            }

            rowIndex += 2;
            XSSFRow row = sheet.createRow(rowIndex);
            row.createCell(1).setCellValue("JAMI");
//            row.createCell(2).setCellValue(Database.getTotalPrice());
            row.createCell(2).setCellFormula(String.format("SUM(C2:C%d)", carList.size()+1));
//            row.createCell(2).setCellFormula(String.format("AVERAGE(C2:C%d)", carList.size()+1));

            workbook.write(out);
        }catch (Exception e){
            e.printStackTrace();
        }

        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
