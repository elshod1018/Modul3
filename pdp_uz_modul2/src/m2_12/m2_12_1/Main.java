package m2_12.m2_12_1;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    static Map<Fruit,Double>fruits=new LinkedHashMap<>();
    public static void main(String[] args) {

        fruits.put(new Fruit("Anor","Quva"),15000d);
        fruits.put( new Fruit("Anor","Tuyatish"),16000d);
        fruits.put(new Fruit("Anor","Surxon"),14000d);
        fruits.put(new Fruit("Olma","Beshyuldiz"),9000d);
        fruits.put(new Fruit("Olma","Golden"),8000d);
        fruits.put(new Fruit("Olma","Qirmizi"),15000d);
        fruits.put(new Fruit("Olma","Semerenka"),6000d);
        fruits.put(new Fruit("Banan","Bananza",5),30000d);
        fruits.put(new Fruit("Banan","Boshqa",5),19000d);
        fruits.put(new Fruit("Shaftoli","Tukli"),6000d);
        fruits.put(new Fruit("Shaftoli","Tuksiz"),8000d);
        fruits.put(new Fruit("Kivi","Import"),21000d);
        fruits.put(new Fruit("Kivi","Mahalliy"),20000d);
        fruits.put(new Fruit("Qulupnay","Yirik"),18000d);
        fruits.put(new Fruit("Qulupnay","Mayda"),9000d);
        fruits.put(new Fruit("Uzum","Qora"),10000d);
        fruits.put(new Fruit("Uzum","Qora"),15000d);
        fruits.put(new Fruit("O’rik","Surxon"),3000d);
        fruits.put(new Fruit("O’rik","Oq"),2000d);
        fruits.put(new Fruit("O’rik","Yirik"),12000d);
        int index=0;
        System.out.println("Map da "+fruits.keySet().size()+" ta meva bor");
        for (Fruit key : fruits.keySet()) {
            if (key.getSale()>0){
                index++;
                System.out.println(index+" => "+key.getName()+" : "+key.getType()
                        +" : "+(fruits.get(key)*(1- key.getSale()/100))+"("+ key.getSale()+" % chegirma bilan )");
            }
            else {
                index++;
                System.out.println(index+" => "+key.getName()+" : "+key.getType()+" : "+fruits.get(key));
            }
        }
    }
}
