package uz.pdp.tasks;

import java.io.*;

public class App4 {
    public static void main(String[] args) {
        File file = new File("files/join.txt");
        File getFile1 = new File("files/b25.txt");
        File getFile2 = new File("files/numbers.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(getFile1));
             BufferedReader reader1 = new BufferedReader(new FileReader(getFile2));
             PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            String s1, s2;
            while (true) {
                s1 = reader.readLine();
                s2 = reader1.readLine();
                if (s1 == null && s2 == null) {
                    break;
                }
               if (s1==null)s1="";
               if (s2==null)s2="";
               writer.write(s1+s2+"\n");

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
