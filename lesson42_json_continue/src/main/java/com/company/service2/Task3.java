package com.company.service2;
import com.company.entity.Todo;
import com.company.entity.User;
import com.company.files.WorkWithFiles;

import java.util.ArrayList;
import java.util.List;

public class Task3 {
    static String BASE_FOLDER = "src/main/resources";

    public static void main(String[] args){
        List<User>userList= WorkWithFiles.getUsers();
        List<Todo>todoList=WorkWithFiles.getTodos();
        List<Integer>doneList=new ArrayList<>();
        List<Integer>unDoneList=new ArrayList<>();
        System.out.printf("%-5s %-25s %-7s %s \n","Id","User name","✅","❌");
        for (User user : userList) {
            int t=0,f=0;
            for (Todo todo : todoList) {
                if (todo.getUserId().equals(user.getId())){
                    if (todo.isCompleted()){
                        t++;
                    }else{
                        f++;
                    }
                }
            }
            doneList.add(t);
            unDoneList.add(f);
        }
        for (int i = 0; i < userList.size(); i++) {
            System.out.printf("%-5s %-25s %-7s %s\n",userList.get(i).getId(),
                    userList.get(i).getName(),doneList.get(i),unDoneList.get(i));
        }
    }
}
