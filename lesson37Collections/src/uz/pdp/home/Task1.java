package uz.pdp.home;

import java.text.CollationElementIterator;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task1 {
    public static void main(String[] args) {
        //1
        Stream.generate(()->new Random().nextInt(100))
                .filter(n->n>=50)
                .filter(n->n<=60)
                .limit(5)
                .forEach(n-> System.out.print("n="+n+"  "));

        //2
        List<Integer> collect = Stream.generate(() -> new Random().nextInt(200))
                .limit(5)
                .collect(Collectors.toList());
        System.out.println();
        System.out.println(collect);
        Collections.shuffle(collect);
        collect.forEach(n-> System.out.print("n="+n+"  "));

        //3

    }
}
