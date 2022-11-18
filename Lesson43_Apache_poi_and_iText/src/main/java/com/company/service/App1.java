package com.company.service;

import com.company.db.Database;
import com.company.entity.Car;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


public class App1 {

    public static void main(String[] args) {
        File file = new File(Database.BASE_DOCS_FOLDER, "cars.docx");
        file.getParentFile().mkdirs();

        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream out = new FileOutputStream(file)) {

            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun run = paragraph.createRun();
            run.setBold(true);
            run.setFontFamily("Consolas");
            run.setFontSize(16);
            run.setText("Cars");

            XWPFTable table = document.createTable();
            table.setWidth("100%");

            XWPFTableRow headerRow = table.getRow(0);

            XWPFTableCell cell00 = headerRow.getCell(0);
            cell00.setWidth("20%");
            cell00.setText("Model");

            XWPFTableCell cell01 = headerRow.createCell();
            cell01.setWidth("15%");
            cell01.setText("Number");

            XWPFTableCell cell02 = headerRow.createCell();
            cell02.setWidth("15%");
            cell02.setText("Price");

            XWPFTableCell cell03 = headerRow.createCell();
            cell03.setWidth("50%");
            cell03.setText("Image");

            List<Car> carList = Database.getCars();

            for (Car car : carList) {
                XWPFTableRow row = table.createRow();
                row.getCell(0).setText(car.getModel());
                row.getCell(1).setText(car.getNumber());
                row.getCell(2).setText(String.valueOf(car.getPrice()));
//                row.getCell(3).setText(car.getImageUrl());

                XWPFParagraph imgParagraph = row.getCell(3).addParagraph();
                XWPFRun imgRun = imgParagraph.createRun();
                FileInputStream fis = new FileInputStream(car.getImageUrl());
                XWPFPicture picture = imgRun.addPicture(fis, XWPFDocument.PICTURE_TYPE_JPEG,
                        "name", Units.pixelToEMU(200), Units.pixelToEMU(200));

                fis.close();
            }

            XWPFParagraph footerParagraph = document.createParagraph();
            footerParagraph.setAlignment(ParagraphAlignment.RIGHT);
            footerParagraph.createRun().setText(
                    "\n"+LocalDateTime.now()+" holatiga ko'ra"
            );

            document.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
