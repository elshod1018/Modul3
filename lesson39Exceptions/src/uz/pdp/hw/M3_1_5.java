package uz.pdp.hw;

import java.util.Scanner;

import static uz.pdp.hw.M3_1_5.WeekDays.*;

public class M3_1_5 {
    static Scanner scanner = new Scanner(System.in);
    static Scanner scannerText = new Scanner(System.in);
    static WeekDays weekDays = null;

    public static void main(String[] args) {
        System.out.print("1 => Uz, 2 => Eng, 3 => Ru: ");
        switch (scanner.nextInt()) {
            case 1:

                break;

        }
    }

    public enum WeekDays {

        MONDAY(),
        TUESDAY(),
        WEDNESDAY(),
        THURSDAY(),
        FRIDAY(),
        SATURDAY(),
        SUNDAY();
        private static String translate;

        WeekDays() {
        }

        WeekDays(String translate) {

        }
    }
}
