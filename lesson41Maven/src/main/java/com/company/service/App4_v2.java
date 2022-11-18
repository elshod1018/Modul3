package com.company.service;

import com.company.entity.Book;
import com.company.util.ScannerUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class App4_v2 {
    static String BASE_FOLDER = "src/main/resources";


    public static void main(String[] args) {
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .setPrettyPrinting()
                .create();

        File file = new File(BASE_FOLDER, "books.json");

        List<Book> bookList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            Type type = new TypeToken<List<Book>>() {
            }.getType();

            bookList = gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Enter title");
        String title = ScannerUtil.SCANNER_STR.nextLine();
        System.out.println("Enter author");
        String author = ScannerUtil.SCANNER_STR.nextLine();
        System.out.println("Enter price");
        double price = ScannerUtil.SCANNER_NUM.nextDouble();

        Book book = new Book(bookList.size() + 1, title, author, price);
        bookList.add(book);


        try (PrintWriter writer = new PrintWriter(file)) {
            writer.write(gson.toJson(bookList));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
