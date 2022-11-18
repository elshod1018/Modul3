package com.company.service2;
import com.company.entity.Todo;
import com.company.entity.User;
import com.company.files.WorkWithFiles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Task2 {
    static String BASE_FOLDER = "src/main/resources";

    public static void main(String[] args) {
        List<User>userList= WorkWithFiles.getUsers();
        List<Todo>todoList=WorkWithFiles.getTodos();

        System.out.printf("%-5s %-25s %-7s %s\n","Id","User name","Done","Title");
        for (Todo todo : todoList) {
            User user1 = userList.stream()
                    .filter(user -> user.getId().equals(todo.getUserId()))
                    .findFirst().orElse(null);
            System.out.printf("%-5s %-25s %-7s %s\n",todo.getId(),user1.getName(),todo.isCompleted(),todo.getTitle());

        }
    }
}
