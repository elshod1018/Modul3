package uz.pdp.localDate;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class App3 {
    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        now=now.with(TemporalAdjusters.nextOrSame(DayOfWeek.MONDAY));
        System.out.println(now);
    }
}
