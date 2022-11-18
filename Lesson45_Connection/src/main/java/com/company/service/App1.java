package com.company.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class App1 {
    public static void main(String[] args) {
        try {
//            URL url = new URL("https://jsonplaceholder.typicode.com/todos");
            URL url = new URL("https://jsonplaceholder.typicode.com/todos?userId=7&id=125");

            System.out.println("url.getProtocol() = " + url.getProtocol());
            System.out.println("url.getHost() = " + url.getHost());
            System.out.println("url.getPort() = " + url.getPort());
            System.out.println("url.getDefaultPort() = " + url.getDefaultPort());

            System.out.println("url.getQuery() = " + url.getQuery());
            System.out.println("url.getPath() = " + url.getPath());
            System.out.println("url.getFile() = " + url.getFile());

        } catch (MalformedURLException e) {

        }
    }
}
