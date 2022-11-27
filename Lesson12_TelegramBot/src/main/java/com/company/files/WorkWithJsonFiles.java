package com.company.files;

import com.company.db.Database;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class WorkWithJsonFiles {
    public static void AddCategoryToJson() {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        File file = new File(Database.MAIN_FILE, "categories.json");

        try (PrintWriter writer = new PrintWriter(file)) {

            writer.write(gson.toJson(Database.CATEGORY_LIST));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void AddOrderToJson() {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        File file = new File(Database.MAIN_FILE, "orders.json");

        try (PrintWriter writer = new PrintWriter(file)) {

            writer.write(gson.toJson(Database.CATEGORY_LIST));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void AddProductToJson() {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        File file = new File(Database.MAIN_FILE, "products.json");

        try (PrintWriter writer = new PrintWriter(file)) {

            writer.write(gson.toJson(Database.CATEGORY_LIST));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void AddOrderDetailToJson() {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        File file = new File(Database.MAIN_FILE, "orderDetails.json");

        try (PrintWriter writer = new PrintWriter(file)) {

            writer.write(gson.toJson(Database.CATEGORY_LIST));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void AddBasketDetailToJson() {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        File file = new File(Database.MAIN_FILE, "basketDetails.json");

        try (PrintWriter writer = new PrintWriter(file)) {

            writer.write(gson.toJson(Database.CATEGORY_LIST));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void AddCustomerToJson() {
        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
        File file = new File(Database.MAIN_FILE, "customers.json");

        try (PrintWriter writer = new PrintWriter(file)) {

            writer.write(gson.toJson(Database.CATEGORY_LIST));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
