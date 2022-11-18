package uz.pdp.hw;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App1 {
    public static void main(String[] args) {
        boolean t = true;
        while (t) {
            try {
                calculate();
                t = false;
            } catch (InputMismatchException e) {
                System.out.println("Son kiriting!");
                Scanner scanner = new Scanner(System.in);
                t = true;
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            } catch (Exception e){
                System.out.println("Some exception.Try again");
            }
        }
    }

    public static void calculate() {
        Scanner scanner = new Scanner(System.in);
        double a, b;
        System.out.print("a=");
        a = scanner.nextInt();
        System.out.print("b=");
        b = scanner.nextInt();
        System.out.println("(a+b) = " + (a + b));
        System.out.println("(a-b) = " + (a - b));
        System.out.println("(a*b) = " + (a * b));
        System.out.println("(a/b) = " + (a / b));
    }
}
