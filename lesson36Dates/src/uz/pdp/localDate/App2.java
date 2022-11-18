package uz.pdp.localDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

public class App2 {
    public static void main(String[] args) {
//        LocalDate localDate=LocalDate.of(2022,1,1);
        LocalDate now = LocalDate.now();
       LocalDate res=null;
//        Month month=now.getMonth();
//        while (now.getMonth().equals(month)){
//            if(now.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
//                res=now;
//                now=now.plusWeeks(1);
//            }
//            else{
//                now=now.plusDays(1);
//            }
//        }
//        System.out.println("localDate = " + res);
        res=now.with(TemporalAdjusters.lastInMonth(DayOfWeek.SATURDAY));
        System.out.println(res);
    }
}
