package uz.pdp.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class App2 {
    public static void main(String[] args) {
        Comparator<String >comparator=(o1, o2) -> {
            if (o1.length()==o2.length())return o1.compareTo(o2);
            return o1.length()-o2.length();
        };
        ArrayList<String> list = new ArrayList<>(List.of(
                "Sarvar", "Sardor", "Azamjon", "Sarvar", "Shodiyorbek", "Zamir","Saador"));

        String max = Collections.max(list,comparator);
        System.out.println("max = " + max);

//        list.sort(comparator); =>
        Collections.sort(list,comparator);
        System.out.println(list);
    }
}