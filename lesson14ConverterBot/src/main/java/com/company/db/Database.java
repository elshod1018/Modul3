package com.company.db;


import com.company.entity.Currency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.impl.store.Cur;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public interface Database {
    static List<Currency> getCurrencies() {
        List<Currency> currencyList = new ArrayList<>();
        try {
            URL url = new URL("https://cbu.uz/oz/arkhiv-kursov-valyut/json/");
            URLConnection urlConnection = url.openConnection();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()))) {

                Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

                Type type = new TypeToken<List<Currency>>() {
                }.getType();

                currencyList = gson.fromJson(reader, type);
                gson.toJson(currencyList);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currencyList;
    }

    static File currenciesPDF() {
        List<Currency> currencies = getCurrencies();
        File file = new File("src/main/resources/currencies.pdf");
        try (PdfWriter writer = new PdfWriter(file);
             PdfDocument pdfDocument = new PdfDocument(writer);
             Document document = new Document(pdfDocument)) {

            Paragraph paragraph = new Paragraph("Currencies");
            paragraph.setTextAlignment(TextAlignment.CENTER);
            paragraph.setBold();

            Table table = new Table(4);

            table.addCell("Currency name");
            table.addCell("Currency Ccy");
            table.addCell("Currency Rate");
            table.addCell("Locale date");

            for (Currency currency : currencies) {
                table.addCell(currency.getCcyNmUZ());
                table.addCell(currency.getCcy());
                table.addCell(currency.getRate());
                table.addCell(currency.getDate());
            }
            document.add(table);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    static File currenciesWord() {
        List<Currency> currencies = getCurrencies();

        File file = new File("src/main/resources/currencies.docx");
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream outputStream = new FileOutputStream(file)) {

            XWPFParagraph paragraph = document.createParagraph();
            paragraph.setAlignment(ParagraphAlignment.CENTER);

            XWPFRun run = paragraph.createRun();
            run.setCapitalized(true);
            run.setText("Currencies");
            run.setFontSize(16);
            run.setBold(true);

            XWPFTable table = document.createTable();
            table.setWidth("100%");

            XWPFTableRow row = table.getRow(0);
            XWPFTableCell cell = row.getCell(0);
            cell.setWidth("30%");
            cell.setText("Name");

            cell = row.createCell();
            cell.setWidth("15%");
            cell.setText("Ccy");

            cell = row.createCell();
            cell.setWidth("25%");
            cell.setText("Rate");

            cell = row.createCell();
            cell.setWidth("30%");
            cell.setText("Date");

            for (Currency currency:currencies) {
                row = table.createRow();
                row.getCell(0).setText(currency.getCcyNmUZ());
                row.getCell(1).setText(currency.getCcy());
                row.getCell(2).setText(currency.getRate());
                row.getCell(3).setText(currency.getDate());
            }

            document.write(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

}
