package com.company.service;

import com.company.entity.Todo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class AppToDos {
    public static void main(String[] args) {
        ObjectMapper mapper=new ObjectMapper();
        try {
            URL url=new URL("https://jsonplaceholder.typicode.com/todos");
            Todo[] todos=mapper.readValue(url, Todo[].class);
            for (Todo todo : todos) {
                System.out.println(todo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
