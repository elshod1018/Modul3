package m3_5;

import java.io.File;
import java.io.IOException;

public class M3_5_1 {
    public static void main(String[] args) {
        File file = new File("src/test.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println(check(file));
        }
    }

    public static String check(File file) {
        if (file.isDirectory()) {
            return "Papka";
        }
        return "File";
    }
}
