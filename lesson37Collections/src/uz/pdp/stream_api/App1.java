package uz.pdp.stream_api;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class App1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(10, 23, 3, 7, -4, 7, 10, 8));
        System.out.println("list = " + list);

        // declarative method
        List<Integer> list1 = new ArrayList<>();
        for (Integer number : list) {
            if(number%2==0){
                list1.add(number);
            }
        }
        System.out.println("list1 = " + list1);

        // imperative method
        List<Integer> list2 = list.stream()
                .filter(number -> number % 2 == 0)
                .toList();
        System.out.println("list2 = " + list2);
    }
}
