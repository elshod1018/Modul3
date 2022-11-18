package uz.pdp.tasks;

import java.io.File;
import java.io.IOException;
import java.time.DayOfWeek;

public class App2 {
    public static void main(String[] args) {
        DayOfWeek[] dayOfWeeks = DayOfWeek.values();
        File parentFile = new File("files/dayofweeks");
        parentFile.mkdirs();
        for (int i = 0; i < dayOfWeeks.length; i++) {
            File file = new File(parentFile, dayOfWeeks[i].name().toLowerCase() + ".txt");
            try {
                file.createNewFile();
//                    file.delete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
