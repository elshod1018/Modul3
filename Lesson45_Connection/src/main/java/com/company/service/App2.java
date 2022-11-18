package com.company.service;

import com.company.entity.Todo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class App2 {
    public static void main(String[] args) {
        try {

            URL url = new URL("https://jsonplaceholder.typicode.com/todos?userId="+"7");

            URLConnection urlConnection = url.openConnection();

            try(BufferedReader reader = new BufferedReader(
                    new InputStreamReader(urlConnection.getInputStream()))) {

                Gson gson = new Gson();

                Type type = new TypeToken<List<Todo>>() {
                }.getType();

                List<Todo> todoList = gson.fromJson(reader, type);

                todoList.forEach(System.out::println);

            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
