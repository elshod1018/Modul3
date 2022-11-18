package uz.pdp.stream_api;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App2 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(10, 23, 3, 7, -4, 7, 10, 8));
        System.out.println("list = " + list);

        boolean allMatch = list.stream().allMatch(n -> n > 0);
        System.out.println("allMatch = " + allMatch);

        boolean anyMatch = list.stream().anyMatch(n -> n < 0);
        System.out.println("anyMatch = " + anyMatch);

        boolean noneMatch = list.stream().noneMatch(n -> n == 100);
        System.out.println("noneMatch = " + noneMatch);

        //Set<Integer> set = list.stream().collect(Collectors.toSet());
        Set<Integer> set = new HashSet<>(list);

        System.out.println("set = " + set);

        System.out.println();

        List<Integer> collect = list.stream()
                .filter(n -> n >= 10)
                .filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        System.out.println("collect = " + collect);

        long count = list.stream()
                .filter(n -> n >= 5)
                .count();
        System.out.println("count = " + count);

        /// distinct
        list.stream()
                .filter(n -> n >= 5)
                .distinct()
                .forEach(System.out::println);

        System.out.println();

        System.out.println("list = " + list);

        Optional<Integer> optional = list.stream()
                .filter(n -> n >= 5)
                .findFirst();
        System.out.println("optional = " + optional);
        if(optional.isPresent()){
            Integer value = optional.get();
            System.out.println("value = " + value);
        }

        System.out.println("\nGrands");
        list.stream()
                .sorted(Collections.reverseOrder())
                .limit(3)
                .forEach(System.out::println);

        System.out.println("\nContracts");
        list.stream()
                .sorted(Collections.reverseOrder())
                .skip(3)
                .limit(4)
                .forEach(System.out::println);

        System.out.println("\nRandom numbers");
        (new Random())
                .ints()
                .limit(10)
                .forEach(System.out::println);

        System.out.println("\nRandom numbers v2");

        Stream.generate(() -> new Random().nextInt(101))
                .limit(10)
                .forEach(System.out::println);


        System.out.println();

        long count1 = Stream.generate(() -> new Random().nextInt(101))
                .limit(16)
                .filter(n -> n >= 60)
                .count();
        System.out.println("count1 = " + count1);


        System.out.println(" \n degrees 2 ");

        Stream.iterate(1, n -> n*2)
                .limit(10)
                .forEach(n -> System.out.println("n = "+n));


        Optional<Integer> reduce11 = Stream.iterate(1, n -> n + 1)
                .limit(6)
                .reduce((n,i)->n*i);
        System.out.println(reduce11.get());
        System.out.println(" \n fibonacci numbers ");

        Stream.iterate(new Fib(0, 1), fib -> new Fib(fib.f1, fib.f0+ fib.f1))
                .limit(10)
                .forEach(fib -> System.out.println("n = "+fib.f0));

        System.out.println();
        System.out.println("list = " + list);

        Optional<Integer> reduce = list.stream()
                .reduce((total, number) -> total + number);
        System.out.println("reduce = " + reduce);
        System.out.println("reduce.get() = " + reduce.get());

//        Integer reduce1 = list.stream()
//                .reduce(0, (total, number) -> total + number);
        Integer reduce1 = list.stream()
                .reduce(0, Integer::sum);
        System.out.println("reduce1 = " + reduce1);

    }

    static class Fib{
        private int f0 = 0;
        private int f1 = 1;

        public Fib(int f0, int f1) {
            this.f0 = f0;
            this.f1 = f1;
        }

        public int getF0() {
            return f0;
        }

        public int getF1() {
            return f1;
        }
    }
}
