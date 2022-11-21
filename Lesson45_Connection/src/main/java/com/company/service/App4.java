package com.company.service;

import com.company.database.Database;
import com.company.entity.Currency;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Scanner;

public class App4 {
    public static void main(String[] args) {
        List<Currency> currencies = Database.getCurrency();
        if (currencies.isEmpty()) return;

        showCurrencies(currencies);

        System.out.print("From ccy: ");
        String fromCcy = new Scanner(System.in).nextLine();

        Currency fromCurrency = getFromCurrency(currencies, fromCcy);
        if (fromCurrency == null) return;

        System.out.print("Enter amount: ");
        double value = new Scanner(System.in).nextDouble();
        double newValue = value * Double.parseDouble(fromCurrency.getRate());
//        System.out.println(value + " " + fromCcy + " = " + newValue + " UZS");

        System.out.print("To ccy: ");
        String toCcy = new Scanner(System.in).nextLine();

        Currency toCurrency = getFromCurrency(currencies, toCcy);
        if (toCurrency == null) return;

        newValue = newValue / Double.parseDouble(toCurrency.getRate());
        System.out.println(value + " " + fromCcy + " = " + newValue + " "+toCcy);
    }

    private static Currency getFromCurrency(List<Currency> currencies, String fromCcy) {
        return currencies.stream()
                .filter(currency -> currency.getCcy().equals(fromCcy))
                .findFirst()
                .orElse(null);
    }

    private static void showCurrencies(List<Currency> currencies) {
        int i = 0;
        while (i < currencies.size()) {
            System.out.printf("%-30s", currencies.get(i).getCcy() + " = " + currencies.get(i).getCcyNmEN());
            i++;
            if (i % 5 == 0) System.out.println();
        }
    }
}

