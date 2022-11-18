package com.company.service2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;

public class App2 {
    static String BASE_FOLDER = "src/main/resources";

    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream(
                new File(BASE_FOLDER, "users.json"))) {

            String source = new String(inputStream.readAllBytes());

            JSONArray jsonArray = new JSONArray(source);
            System.out.println("jsonArray.length() = " + jsonArray.length());

            System.out.println();

            JSONObject userObject = (JSONObject) jsonArray.get(4);

            JSONObject companyObject = userObject.getJSONObject("company");

            String companyName = companyObject.getString("name");

            System.out.println("companyName = " + companyName);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
