package m3_5;

import java.io.File;
import java.util.Arrays;

public class M3_5_3 {
    public static void main(String[] args) {
        File folder = new File("./");

        showInFolder(folder,"m");
    }

    static  void showInFolder(File folder,String str){
        for (File file : folder.listFiles()) {
            if (file.getName().contains(str)) {
                System.out.println(file.getName());
            }
            if(file.isDirectory()){
                showInFolder(file,str);
            }
        }
    }

}
