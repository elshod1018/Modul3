package uz.pdp;
import java.time.LocalTime;
import java.util.Scanner;

public class UzPdp {
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args) {
        getTime();
    }
    public static void getTime(){
        String localTime;
        System.out.print("Vaqtni kiriting(hh:mm:ss): ");
        localTime=scanner.nextLine();
        LocalTime localTime1=LocalTime.now();
    }
}
