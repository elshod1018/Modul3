package com.company.service;

import com.company.database.Database;
import com.company.entity.Currency;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Converter {
    static Scanner scannerText = new Scanner(System.in);
    static Scanner scannerNumber = new Scanner(System.in);
    static List<Currency> currencies = Database.getCurrency();

    public static void main(String[] args) {
        converter();
    }

    public static void converter() {
        while (true){
            System.out.println("""
                0 => Exit
                1 => Convert
                2 => Currencies:\s""");
            switch (scannerText.nextLine()) {
                case "0":
                    return;
                case "1":
                    convert();
                    break;
                case "2":
                    currencies.stream().
                            forEach(currency -> System.out.println(currency.getCcyNmUZ()+" => "+currency.getCcy()));
                    break;
                default:
                    System.out.println("Wrong command !");
            }
        }
    }

    public static void convert() {
        String 
    }
}
