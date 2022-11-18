package uz.pdp.iostream;

import java.io.*;

public class TextWorking {
    public static void main(String[] args) {
        File file=new File("D:\\AJavacodes\\modul3\\lesson39Exceptions\\src\\uz\\pdp\\iostream\\1.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
