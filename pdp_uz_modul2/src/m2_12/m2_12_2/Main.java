package m2_12.m2_12_2;

import m2_12.m2_12_1.Fruit;

import java.util.Comparator;
import java.util.HashMap;
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
        System.out.println("1-topshiriq: ");
        int index = 0;
        for (Fruit fruit : fruits.keySet()) {
            if (fruit.getName().charAt(0) == 'A') {
                index++;
                System.out.println(index + " => " + fruit.getName() + " : " + fruit.getType() + " : " + fruits.get(fruit));
            }
        }
        //         2-topshiriq
        System.out.println("\n\n2-topshiriq: ");
        index = 0;
        for (Fruit fruit : fruits.keySet()) {
            if (fruits.get(fruit) > 5000) {
                index++;
                System.out.println(index + " => " + fruit.getName() + " : " + fruit.getType() + " : " + fruits.get(fruit));
            }
        }
        //         3-topshiriq
        fruits.replaceAll((f, v) -> fruits.get(f) * (1 - f.getSale() / 100));
        System.out.println("Barcha meva narxlari 10 % arzonlashtirildi");
        //         4- tophiriq
        fruits.replaceAll((fruit, price) ->fruits.get(fruit)>10000?fruits.get(fruit) * 0.9:fruits.get(fruit));
        System.out.println("10000 dan qimmat barcha mevalar narxlari 10% arzonlashtirildi");
        //         5- tophiriq
        fruits.replaceAll((fruit, price) ->{
            if (fruits.get(fruit)>20000){
                price=fruits.get(fruit) * 0.7;
                fruit.setSale(10);
            }
            return price;
        });
        System.out.println("20000 dan qimmat barcha mevalar narxlari 30% arzonlashtirildi va 10 % chegirma joriy qilindi");
    }
}
