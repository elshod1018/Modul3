package uz.pdp.tasks;

import java.io.*;
import java.util.*;

import static com.sun.tools.attach.VirtualMachine.list;

public class App5 {
    public static void main(String[] args) {
        File file = new File("files/reytings.txt");
        File writFile = new File("files/sort.txt");
        if (!writFile.exists()) {
            try {
                writFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             BufferedWriter writer = new BufferedWriter(new FileWriter(writFile))) {
            String line;
            TreeMap<Double, ArrayList<String>> map = new TreeMap<>(Collections.reverseOrder());
            while ((line = reader.readLine()) != null) {

                String s[] = line.split("=");
                String name = s[0].trim();

                double s1 = Double.parseDouble(s[1].trim());

                if (map.containsKey(s1)) {
                    map.get(s1).add(name);
                }
                else {
                    map.put(s1, new ArrayList<>(List.of(name)));
                }

            }

            for (Double key : map.keySet()) {
                Collections.sort(map.get(key));
            }
            map.forEach((key, value) -> map.get(key).stream().forEach(s2 -> {
                try {
                    writer.write(s2 + " " + key + " ball\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
