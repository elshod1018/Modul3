package uz.pdp.tasks;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class App6 {
    public static void main(String[] args) {
        File file = new File("files/data.txt");
        ArrayList<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s;
            while ((s = reader.readLine()) != null) {
                String t[] = s.split("[\\.\\-\\ \\:\\,]");
                words.addAll(List.of(t));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(words);
        String max=words.get(0);
        for (String word : words) {
            if (word.length()>max.length()){
                max=word;
            }
        }
        System.out.println(max);
        File file1=new File("files/numbers.txt");
        File file2=new File("files/words.txt");
        int sum=0;
        for (String word : words) {
            if (word.matches("\\d+")){
                try (PrintWriter writer = new PrintWriter(new FileWriter(file1,true))) {
                    writer.write(word+"\n");
                    sum+=Double.parseDouble(word);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            if(word.matches("[A-Za-z]+")){
                try (PrintWriter writer = new PrintWriter(new FileWriter(file2, false))) {
                    writer.write(word+"\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println("sum = " + sum);
    }
}
