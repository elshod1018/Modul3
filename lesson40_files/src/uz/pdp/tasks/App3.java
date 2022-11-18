package uz.pdp.tasks;

import java.io.*;
import java.util.ArrayList;

public class App3 {
    public static void main(String[] args) {
        File file=new File("files/b25.txt");
        file.mkdirs();
        if (!file.exists()){
            try {
                boolean newFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String s;
            String max= reader.readLine();
            ArrayList<String>list=new ArrayList<>();
            while((s=reader.readLine())!=null){
                if (max.length()<s.length()){
                    max=s;
                    list.clear();
                    list.add(max);
                }
                if (max.length()==s.length()){
                    list.add(s);
                }
            }
            System.out.println(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
