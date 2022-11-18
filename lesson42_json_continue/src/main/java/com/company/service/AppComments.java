package com.company.service;

import com.company.entity.Comment;
import com.company.entity.Post;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class AppComments {
    public static void main(String[] args) {
        ObjectMapper mapper=new ObjectMapper();
        try {
            URL url=new URL("https://jsonplaceholder.typicode.com/comments");
            Comment[] comments=mapper.readValue(url, Comment[].class);
            for (Comment comment : comments) {
                System.out.println(comment+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
