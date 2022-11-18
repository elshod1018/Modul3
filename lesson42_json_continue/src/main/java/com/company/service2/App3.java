package com.company.service2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class App3 {
    static String BASE_FOLDER = "src/main/resources";

    public static void main(String[] args) {
        try (FileInputStream inputStream = new FileInputStream(
                new File(BASE_FOLDER, "users.json"))) {

            String source = new String(inputStream.readAllBytes());

            JSONArray jsonArray = new JSONArray(source);
            System.out.println("jsonArray.length() = " + jsonArray.length());

            System.out.println();

            System.out.printf("%-25s %-25s %s \n\n", "name", "city", "company name");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject userObject = (JSONObject) jsonArray.get(i);

                String name = userObject.getString("name");

                JSONObject addressObject = userObject.getJSONObject("address");

                String city = addressObject.getString("city");

                JSONObject companyObject = userObject.getJSONObject("company");

                String companyName = companyObject.getString("name");

                System.out.printf("%-25s %-25s %s \n", name, city, companyName);
            }







        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
