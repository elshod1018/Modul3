package uz.pdp.hw;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App2_2 {
    public static void main(String[] args) {
        Scanner scannerInt = new Scanner(System.in);
        Scanner scannerStr = new Scanner(System.in);

        System.out.println("Satr kiriting :");
        String text = scannerStr.nextLine();
        try {
            System.out.println("a: ");
            int a = scannerInt.nextInt();

            System.out.println("b: ");
            int b = scannerInt.nextInt();
            if (b<=text.length()){
                System.out.println(text.substring(a, b));
            }

        }catch (InputMismatchException | StringIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e){
            System.out.println("Qiymatni to'g'ri kirirting");
        }
    }
}
