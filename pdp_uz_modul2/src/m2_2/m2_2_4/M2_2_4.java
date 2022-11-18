package m2_2.m2_2_4;

import java.util.ArrayList;
import java.util.List;

public class M2_2_4 {
    public static void main(String[] args) {
        Book book=new Book("Urush yillari",new ArrayList<>(List.of("Abdulla qodiriy","O'tkir Hoshimov")),
                "012345689112");
        System.out.println("Name: "+book.getName());
        printAuthors(book);
    }
    public static void printAuthors(Book book){
        System.out.print("Authors: ");
        for (String author : book.getAuthors()) {
            System.out.print(author+"   ");
        }
    }
}
