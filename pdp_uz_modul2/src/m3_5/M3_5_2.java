package m3_5;

import java.io.File;
import java.util.Arrays;

public class M3_5_2 {
    public static void main(String[] args) {
        File file=new File("./src/m3_5");
        System.out.println(check(file));
    }
    public static String check(File file){
        File[] files = file.listFiles();
        int t=0;
        for (File file1 : files) {
            if (file1.isDirectory()){
                t++;
            }
        }
        if (t== files.length){
            return "Papkalar";
        }
        t=0;
        for (File file1 : files) {
            if (!file1.isDirectory()){
                t++;
            }
        }
        if (t== files.length){
            return "Fayllar";
        }
        return "Papka va Fayllar";
    }
}
