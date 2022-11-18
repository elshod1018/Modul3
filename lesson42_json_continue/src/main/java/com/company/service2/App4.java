package com.company.service2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class App4 {
    static String BASE_FOLDER = "src/main/resources";

    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream(
                new File(BASE_FOLDER, "users.json"))) {

            String source = new String(inputStream.readAllBytes());

            JSONArray jsonArray = new JSONArray(source);
            System.out.println("jsonArray.length() = " + jsonArray.length());

            System.out.println();

            System.out.printf("%-25s %-25s %s \n\n", "name", "username", "phone number");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userObject = (JSONObject) jsonArray.get(i);

                String name = userObject.getString("name");

                String userName = userObject.getString("username");
                String phone = userObject.getString("phone");

                System.out.printf("%-25s %-25s %s \n", name, userName, phone);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
