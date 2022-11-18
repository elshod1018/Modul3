package m3_5;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class App5 {
    public static void main(String[] args) {
//        java.io.
//        java.nio.

        File srcFolder = new File("C:/Users/NBU/Downloads/Telegram Desktop");
        File destFolder = new File("C:/Users/NBU/Downloads/sorted_telegram");

        Map<String, List<String>> directoryMap = new HashMap<>();
        directoryMap.put("images", List.of(".jpg", ".jpeg", ".bmp", ".png"));
        directoryMap.put("videos", List.of(".mp4", ".avi", ".wmv"));
        directoryMap.put("audios", List.of(".mp3", ".audio"));
        // others:  documents

        for (File file : srcFolder.listFiles()) {
            if (file.isFile()) {
                String fileName = file.getName();
                String extension = fileName.substring(fileName.lastIndexOf("."));

                String directory = "documents";

                for (String key : directoryMap.keySet()) {
                    if (directoryMap.get(key).contains(extension.toLowerCase())) {
                        directory = key;
                        break;
                    }
                }

                String newFilename = destFolder.getAbsolutePath() + "/" + directory + "/" + UUID.randomUUID()+extension;

                new File(newFilename).getParentFile().mkdirs();

                try {
                    Files.copy(Path.of(file.getAbsolutePath()), Path.of(newFilename));
                } catch (IOException e) {
                    e.printStackTrace();
                }


//                String prefix = "";
//
//                while (true) {
//                    try {
//                        fileName = prefix + fileName;
//                        String newFilename = destFolder.getAbsolutePath() + "/" + directory + "/" + fileName;
//
//                        new File(newFilename).getParentFile().mkdirs();
//
//                        Files.copy(Path.of(file.getAbsolutePath()), Path.of(newFilename));
//                        break;
//                    } catch (FileAlreadyExistsException e) {
//                        prefix = String.valueOf(Calendar.getInstance().getTimeInMillis());
//                    } catch (IOException e) {
//
//                    }
//                }
            }
        }

    }
}
