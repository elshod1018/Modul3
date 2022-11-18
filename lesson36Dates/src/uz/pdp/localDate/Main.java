package uz.pdp.localDate;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Calendar start = Calendar.getInstance();
        start.set(2022, 0, 1);
       while (start.get(Calendar.DAY_OF_WEEK)!=Calendar.FRIDAY){
           start.add(Calendar.DAY_OF_WEEK,1);
       }
       start.add(Calendar.WEEK_OF_YEAR,32);
        System.out.println(start.getTime());
    }
}
