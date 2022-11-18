package uz.pdp.localDate;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class Main3 {
    public static void main(String[] args) {
        LocalDate localDate=LocalDate.of(2022,1,1);
        while (localDate.getDayOfWeek()!= DayOfWeek.SUNDAY){
            localDate=localDate.plusDays(1);
        }
        while (localDate.getYear()==2022){
            System.out.println(localDate);
            localDate=localDate.plusDays(7);
        }
    }
}
