package uz.pdp.tasks;

import java.io.File;
import java.io.IOException;

public class App1 {
    public static void main(String[] args) {
        File file=new File("./files/b25.txt");
        if (!file.exists()){
            File parentFile=file.getParentFile();
            boolean mkdirs = parentFile.mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
