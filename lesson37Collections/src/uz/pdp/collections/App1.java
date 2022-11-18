package uz.pdp.collections;

import java.util.*;

public class App1 {
    public static void main(String[] args) {
//        int[] arr = {10, 20, 30};
//        System.out.println(Arrays.toString(arr));

//        Collections

        List<Integer> list = new ArrayList<>(List.of(10, 230, 30, 7, -4, 7, 10, 8));
        System.out.println("list = " + list);

        //        list.add(25);
//        list.add(15);
        Collections.addAll(list, 25, 15);

        System.out.println("list = " + list);

        System.out.println();
        //Collections.sort(list);
        Collections.sort(list, Collections.reverseOrder());

        System.out.println("list = " + list);

        System.out.println("list.indexOf(7) = " + list.indexOf(7));
        System.out.println("list.contains(7) = " + list.contains(7));

        int binarySearch = Collections.binarySearch(list, 7, Collections.reverseOrder());
        System.out.println("binarySearch = " + binarySearch);

        binarySearch = Collections.binarySearch(list, 230, Collections.reverseOrder());
        System.out.println("binarySearch = " + binarySearch);

        System.out.println();
        Collections.reverse(list);

        System.out.println("list = " + list);

        System.out.println();
        List<Integer> listCopy = new ArrayList<>(15);
        for (int i = 0; i < 15; i++) {
            listCopy.add(i);
        }
        System.out.println("listCopy = " + listCopy);

        Collections.fill(listCopy, 1);
        System.out.println("listCopy = " + listCopy);

        Collections.copy(listCopy, list);
        System.out.println("listCopy = " + listCopy);
        System.out.println("list = " + list);

        System.out.println();
        listCopy.removeAll(list);
        listCopy.add(7);

        System.out.println("listCopy = " + listCopy);

        boolean disjoint = Collections.disjoint(list, listCopy);
        System.out.println("disjoint = " + disjoint);

        System.out.println("list = " + list);

        System.out.println("Collections.frequency(list, 7) = " +
                Collections.frequency(list, 7));

        System.out.println("Collections.frequency(list, 1000) = " +
                Collections.frequency(list, 1000));

        List<Integer>m=new ArrayList<>();
        for (Integer integer : new HashSet<>(list)) {
            m.add(Collections.frequency(list,integer));
        }
       int max=Collections.max(m);
        for (Integer integer : new HashSet<>(list)) {
            if(max==Collections.frequency(list,integer)){
                System.out.println(integer+"- >"+max);
            }
        }
    }
}
