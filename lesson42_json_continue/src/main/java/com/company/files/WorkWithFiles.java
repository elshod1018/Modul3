package com.company.files;

import com.company.entity.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class WorkWithFiles {
    public static List<Todo> getTodos(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Todo[] todos = mapper.readValue(
                    new URL("https://jsonplaceholder.typicode.com/todos"), Todo[].class);

            return new ArrayList<>(List.of(todos));
        } catch (IOException e) {

        }
        return new ArrayList<>();
    }

    public static List<User> getUsers(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            User[] users = mapper.readValue(
                    new URL("https://jsonplaceholder.typicode.com/photos"), User[].class);

            return new ArrayList<>(List.of(users));
        } catch (IOException e) {

        }
        return new ArrayList<>();
    }
    public static List<Post> getPosts(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Post[] posts = mapper.readValue(
                    new URL("https://jsonplaceholder.typicode.com/posts"), Post[].class);

            return new ArrayList<>(List.of(posts));
        } catch (IOException e) {

        }
        return new ArrayList<>();
    }
    public static List<Comment> getComments(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Comment[] comments = mapper.readValue(
                    new URL("https://jsonplaceholder.typicode.com/comments"), Comment[].class);

            return new ArrayList<>(List.of(comments));
        } catch (IOException e) {

        }
        return new ArrayList<>();
    }
    public static List<Photo> getPhotos(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Photo[] photos = mapper.readValue(
                    new URL("https://jsonplaceholder.typicode.com/photos"), Photo[].class);

            return new ArrayList<>(List.of(photos));
        } catch (IOException e) {

        }
        return new ArrayList<>();
    }
    public static List<Album> getAlbums(){
        ObjectMapper mapper = new ObjectMapper();
        try {
            Album[] albums = mapper.readValue(
                    new URL("https://jsonplaceholder.typicode.com/albums"), Album[].class);

            return new ArrayList<>(List.of(albums));
        } catch (IOException e) {

        }
        return new ArrayList<>();
    }
}
