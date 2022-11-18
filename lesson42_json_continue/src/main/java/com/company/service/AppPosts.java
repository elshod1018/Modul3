package com.company.service;

import com.company.entity.Post;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class AppPosts {
    public static void main(String[] args) {
        ObjectMapper mapper=new ObjectMapper();
        try {
            URL url=new URL("https://jsonplaceholder.typicode.com/posts");
            Post[] posts=mapper.readValue(url,Post[].class);
            for (Post post : posts) {
                System.out.println(post+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
