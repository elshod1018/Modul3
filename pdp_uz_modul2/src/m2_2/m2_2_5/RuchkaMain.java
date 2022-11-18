package m2_2.m2_2_5;

import java.util.Scanner;

public class RuchkaMain {
    static Scanner scannerText = new Scanner(System.in);
    static Scanner scannerNumbers = new Scanner(System.in);

    public static void main(String[] args) {
        Ruchka bluePen = new Ruchka("Blue", 100, 10);
        int command;
        String text;
        while (true) {
            System.out.println();
            System.out.print("""
                    0 ==> Exit
                    1 ==> Tugmani bosish
                    2 ==> Yozish
                    3 ==> Siyohni almashtirish
                    4 ==> Siyoh darajasi:\s""");
            command = scannerNumbers.nextInt();
            switch (command) {
                case 0:
                    return;
                case 1:
                    bluePen.clickButton();
                    break;
                case 2:
                    if (bluePen.isButton()) {
                        System.out.print("Yozmoqchi bo'lgan matningizni kiriting: ");
                        text = scannerText.nextLine();
                        bluePen.write(text);
                        System.out.println();
                    } else {
                        System.out.println("Yozishdan oldin tugmani bosing");
                    }
                    break;
                case 3:
                    bluePen.changeInk();
                    break;
                case 4:
                    System.out.println(bluePen.getInkRate());
                    break;
                default:
                    System.out.println("Buyruq noto'g'ri tanlandi");
                    break;
            }
        }
    }
}
