package uz.pdp.localDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class App4_v2 {
    public static void main(String[] args) {
        LocalDate now = LocalDate.of(2022,1,1);
        int dayOff=0;
        now=now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        while (now.getYear()==2022){
            dayOff++;
            now=now.plusWeeks(1);
        }
        now = LocalDate.of(2022,1,1);
        now=now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
        while (now.getYear()==2022){
            dayOff++;
            now=now.plusWeeks(1);
        }
        System.out.println(dayOff);
    }
}
