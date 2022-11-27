package com.company.db;

import com.company.entity.*;
import com.company.service.CategoryService;
import com.company.service.CustomerService;
import com.company.service.ProductService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public interface Database {
    String MAIN_FILE = "src/main/resources/documents";
    List<Category> CATEGORY_LIST = getCategoryList();
    List<Product> PRODUCT_LIST = getProductList();
    List<Customer> CUSTOMER_LIST = getCustomerList();
    List<BasketDetail> BASKET_DETAIL_LIST = getBasketDetailList();
    List<Order> ORDER_LIST = getOrderList();
    List<OrderDetail> ORDER_DETAIL_LIST = getOrderDetailList();

    static List<Category> getCategoryList() {

        File file = new File(MAIN_FILE, "categories.json");
        file.getParentFile().mkdirs();

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Type type = new TypeToken<List<Category>>() {
            }.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }



    static List<Product> getProductList() {

        File file = new File(MAIN_FILE, "products.json");
        file.getParentFile().mkdirs();

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Type type = new TypeToken<List<Product>>() {
            }.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    static List<Customer> getCustomerList() {

        File file = new File(MAIN_FILE, "customers.json");
        file.getParentFile().mkdirs();

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Type type = new TypeToken<List<Customer>>() {
            }.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    static List<BasketDetail> getBasketDetailList() {

        File file = new File(MAIN_FILE, "basketDetails.json");
        file.getParentFile().mkdirs();

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Type type = new TypeToken<List<BasketDetail>>() {
            }.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    static List<Order> getOrderList() {

        File file = new File(MAIN_FILE, "orders.json");
        file.getParentFile().mkdirs();

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Type type = new TypeToken<List<Order>>() {
            }.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    static List<OrderDetail> getOrderDetailList() {

        File file = new File(MAIN_FILE, "orderDetails.json");
        file.getParentFile().mkdirs();

        Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            Type type = new TypeToken<List<OrderDetail>>() {
            }.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

//    static void loadData() {
//        CategoryService.addCategory("Tv");
//        CategoryService.addCategory("Phone");
//        CategoryService.addCategory("Fridge");
//        CategoryService.addCategory("Watch");
//
//        ProductService.addProduct(new Product(null, 2, "Redmi 9", 150d, "Normal phone",
//                "AgACAgIAAxkBAAIBdmN9_Qkbd1Qrp_2LIdUVyWJ73IoFAALmvzEb2OXwSz-FGvUN7vgdAQADAgADeAADKwQ"));
//
//        ProductService.addProduct(new Product(null, 1, "LG", 200d, "Normal tv",
//                "AgACAgIAAxkBAAIBaWN9_MjrTijCs02iUEhcFmLsQRkFAALjvzEb2OXwS90hN2-QEtAbAQADAgADeAADKwQ"));
//
//        CustomerService.addCustomerByChatId("5606813390");
//        CustomerService.addCustomerByChatId("768177376");
//        CustomerService.addCustomerByChatId("1586002925");
//        CustomerService.addCustomerByChatId("1117887461");
//        CustomerService.addCustomerByChatId("1132376865");
//
//    }
}
