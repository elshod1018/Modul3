package com.company.service;

import com.company.entity.Album;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class AppAlbums {
    public static void main(String[] args) {
        ObjectMapper mapper=new ObjectMapper();
        try {
            URL url=new URL("https://jsonplaceholder.typicode.com/posts");
            Album[] albums=mapper.readValue(url,Album[].class);
            for (Album album : albums) {
                System.out.println(album+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
