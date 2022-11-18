package m2_12.m2_12_3;

import m2_12.m2_12_1.Fruit;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<Fruit, Double> fruits = new LinkedHashMap<>();
        fruits.put(new Fruit("Anor", "Quva"), 15000d);
        fruits.put(new Fruit("Anor", "Tuyatish"), 16000d);
        fruits.put(new Fruit("Anor", "Surxon"), 14000d);
        fruits.put(new Fruit("Olma", "Beshyuldiz"), 9000d);
        fruits.put(new Fruit("Olma", "Golden"), 8000d);
        fruits.put(new Fruit("Olma", "Qirmizi"), 15000d);
        fruits.put(new Fruit("Olma", "Semerenka"), 6000d);
        fruits.put(new Fruit("Banan", "Bananza", 5), 30000d);
        fruits.put(new Fruit("Banan", "Boshqa", 5), 19000d);
        fruits.put(new Fruit("Shaftoli", "Tukli"), 6000d);
        fruits.put(new Fruit("Shaftoli", "Tuksiz"), 8000d);
        fruits.put(new Fruit("Kivi", "Import"), 21000d);
        fruits.put(new Fruit("Kivi", "Mahalliy"), 20000d);
        fruits.put(new Fruit("Qulupnay", "Yirik"), 18000d);
        fruits.put(new Fruit("Qulupnay", "Mayda"), 9000d);
        fruits.put(new Fruit("Uzum", "Qora"), 10000d);
        fruits.put(new Fruit("Uzum", "Qora"), 15000d);
        fruits.put(new Fruit("O’rik", "Surxon"), 3000d);
        fruits.put(new Fruit("O’rik", "Oq"), 2000d);
        fruits.put(new Fruit("O’rik", "Yirik"), 12000d);
        //         1-topshiriq
        Iterator<Fruit>iterator=fruits.keySet().iterator();
        double sum=0;
        while (iterator.hasNext()){
            sum+=fruits.get(iterator.next());
        }
        System.out.println("Mevalarning o'rtacha narxi: "+sum/fruits.keySet().size());
        //         2-topshiriq
        fruits.replaceAll((f, v) -> {
            if (fruits.get(f).equals("Olma")) {
                v=v*1.05;
            }
            return v;
        });
        System.out.println("Barcha olma narxlari 5 % qimmatlashtirildi");
        //         3-topshiriq
        fruits.keySet().forEach(Fruit::getName);
        //         4- tophiriq
        fruits.keySet().forEach(System.out::println);
        //         5- tophiriq
        fruits.keySet().forEach(fruit -> System.out.println(fruits.get(fruit)));
         }
}
