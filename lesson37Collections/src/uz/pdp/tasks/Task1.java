package uz.pdp.tasks;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task1 {
    public static void main(String[] args) {
        //task1
        Stream.generate(() -> new Random().nextInt(100))
                .filter(random -> random % 2 != 0)
                .limit(5)
                .forEach(n -> System.out.println("n=" + n));
        //task2
        System.out.println();
        ArrayList<Integer> collect = Stream.generate(() -> new Random().nextInt(100))
                .limit(5)
                .collect(Collectors.toCollection(ArrayList::new));
        Optional<Integer> max = collect.stream()
                .max(Comparator.comparingInt(o -> o));
        System.out.println(collect);
        System.out.println(max);
        //task3
//        System.out.println();
//        ArrayList<Integer> collect1= Stream.generate(() -> new Random().nextInt(100))
//                .filter(random -> random % 2 == 0)
//                .limit(10)
//                .collect(Collectors.toCollection(ArrayList::new));
//        Optional<Integer> sum = collect.stream()
//                .reduce(Integer::sum);
//        System.out.println(collect1);
//        System.out.println(sum);
        List<Integer> list = Stream.iterate(2, n -> n + 2)
                .limit(10).toList();

        list.forEach(System.out::println);

       String s="abcde",goal="cdeab";
        for (int i = 0; i < s.length(); i++) {
            s=s.substring(1)+s.charAt(0);
            if (s.equals(goal)){
                System.out.println(true);
            }
        }
    }
}
