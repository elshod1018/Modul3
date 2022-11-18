package uz.pdp.localDate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Main2 {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2003, 10, 18);
        LocalDate localDate1 = LocalDate.parse("18.10.2003", DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        System.out.println("(dayOfWeek) = " + (dayOfWeek));
        System.out.println("localDate = " + localDate);
        LocalDate shodiyor = LocalDate.of(2007, 2, 17);
        long days = 0;
        while (localDate.isBefore(LocalDate.now())) {
            days++;
            localDate = localDate.plusDays(1);
        }
        System.out.println("days = " + days);
    }
}
