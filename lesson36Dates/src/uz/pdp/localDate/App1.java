package uz.pdp.localDate;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class App1 {
    public static void main(String[] args) {
//        find 33- Friday
        LocalDate localDate=LocalDate.of(2022,1,1);
        while (localDate.getDayOfWeek()!= DayOfWeek.FRIDAY){
            localDate=localDate.plusDays(1);
        }
       localDate=localDate.plusWeeks(32);
        System.out.println("localDate = " + localDate);
    }
}
