package com.company.db;

import com.company.entity2.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public interface Database2 {
    String MAIN_DOCS = "src/main/resources/tasks/docs";
    String MAIN_EXCELS = "src/main/resources/tasks/excels";
    String MAIN_PDFS = "src/main/resources/tasks/pdfs";

    static List<User> getUsers() {
        List<User> users = new ArrayList<>();
        File file = new File("src/main/resources/jsons/users.json");
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Type type = new TypeToken<List<User>>() {
            }.getType();
            users = gson.fromJson(reader, type);
            return users;
        } catch (IOException e) {
            return users;
        }
    }

    static List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();
        File file = new File("src/main/resources/jsons/comments.json");
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Type type = new TypeToken<List<Comment>>() {
            }.getType();
            comments = gson.fromJson(reader, type);
            return comments;
        } catch (IOException e) {
            return comments;
        }
    }

    static List<Photo> getPhoto() {
        List<Photo> photos = new ArrayList<>();
        File file = new File("src/main/resources/jsons/photos.json");
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Type type = new TypeToken<List<Photo>>() {
            }.getType();
            photos = gson.fromJson(reader, type);
            return photos;
        } catch (IOException e) {
            return photos;
        }
    }

    static List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        File file = new File("src/main/resources/jsons/posts.json");
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Type type = new TypeToken<List<Post>>() {
            }.getType();
            posts = gson.fromJson(reader, type);
            return posts;
        } catch (IOException e) {
            return posts;
        }
    }

    static List<Album> getAlbums() {
        List<Album> albums = new ArrayList<>();
        File file = new File("src/main/resources/jsons/posts.json");
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Type type = new TypeToken<List<Album>>() {
            }.getType();
            albums = gson.fromJson(reader, type);
            return albums;
        } catch (IOException e) {
            return albums;
        }
    }

    static List<Todo> getTodos() {
        List<Todo> todos = new ArrayList<>();
        File file = new File("src/main/resources/jsons/todos.json");
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Type type = new TypeToken<List<Todo>>() {
            }.getType();
            todos = gson.fromJson(reader, type);
            return todos;
        } catch (IOException e) {
            return todos;
        }
    }
}
