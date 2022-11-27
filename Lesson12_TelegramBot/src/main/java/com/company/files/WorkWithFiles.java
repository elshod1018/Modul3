package com.company.files;


import com.company.db.Database;
import com.company.entity.Category;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class WorkWithFiles {
    public static File getCategoriesWithPDF() {
        File file = new File("src/main/resources/documents", "categories.pdf");
        file.getParentFile().mkdirs();

        try (PdfWriter pdfWriter = new PdfWriter(file);
             PdfDocument pdfDocument = new PdfDocument(pdfWriter);
             Document document = new Document(pdfDocument);
        ) {


            Paragraph paragraph = new Paragraph("Category list");
            paragraph.setTextAlignment(TextAlignment.CENTER);

            document.add(paragraph);

            Table table = new Table(new float[]{50, 250});

            table.addCell("ID");
            table.addCell("NAME");

            for (Category category : Database.CATEGORY_LIST) {
                table.addCell(String.valueOf(category.getId()));
                table.addCell(category.getName());
            }

            document.add(table);

        } catch (IOException e) {
            return null;
        }

        return file;
    }
}
