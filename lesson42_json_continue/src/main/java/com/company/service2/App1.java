package com.company.service2;

import org.json.JSONObject;

public class App1 {
    public static void main(String[] args) {
        JSONObject bookObject = new JSONObject();

        bookObject.put("id", 77);
        bookObject.put("title", "C++");
        bookObject.put("author", "Bjarne");

        System.out.println("bookObject = " + bookObject);
    }
}
