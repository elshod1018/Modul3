package com.company.service;

import com.company.db.Database;
import com.company.entity.Car;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class App3 {
    public static void main(String[] args) {
        File file = new File(Database.BASE_DOCS_FOLDER, "cars.pdf");
        file.getParentFile().mkdirs();

        try (PdfWriter pdfWriter = new PdfWriter(file);
             PdfDocument pdfDocument = new PdfDocument(pdfWriter);
             Document document = new Document(pdfDocument)) {

            Paragraph paragraph = new Paragraph("Cars");
            paragraph.setTextAlignment(TextAlignment.CENTER);
            document.add(paragraph);

            List<Car> carList = Database.getCars();

            float[] columns = {75, 75, 75, 250};
            Table table = new Table(columns);

            table.addCell("Model");
            table.addCell("Number");
            table.addCell("Price");
            table.addCell("Image");

            for (Car car : carList) {
                table.addCell(car.getModel());
                table.addCell(car.getNumber());
                table.addCell(String.valueOf(car.getPrice()));
//                table.addCell(car.getImageUrl());

                // Creating an ImageData object
                String imgFile = car.getImageUrl();
                ImageData data = ImageDataFactory.create(imgFile);
                // Creating an Image object
                Image image = new Image(data);
                // Adding image to the document
                table.addCell(image.setAutoScale(true));
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
