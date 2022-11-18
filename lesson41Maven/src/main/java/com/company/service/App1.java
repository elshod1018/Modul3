package com.company.service;

import com.company.entity.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App1 {
    static String base_package="src/main/resources";
    public static void main(String[] args) {
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(10,"Java","Asad",50d));
        bookList.add(new Book(20,"JavaScript","Sobir",30d));
        bookList.add(new Book(30,"Java","Sobir",50d));

        File file = new File(base_package,"book.json");
        try {
            file.createNewFile();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(bookList);
            byte[] bytes = s.getBytes();
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
