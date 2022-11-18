package com.company.service;

import com.company.entity.Photo;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class AppPhotos {
    public static void main(String[] args) {
        ObjectMapper mapper=new ObjectMapper();
        try {
            URL url=new URL("https://jsonplaceholder.typicode.com/posts");
            Photo[] photos=mapper.readValue(url,Photo[].class);
            for (Photo photo : photos) {
                System.out.println(photo+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
