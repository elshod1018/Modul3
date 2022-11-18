package m2_3.m2_3_1.uz.pdp.online.model;

import java.util.Scanner;

public class Main {
    static Scanner scannerText=new Scanner(System.in);
//    static Scanner scannerNumber=new Scanner(System.in);
    public static void main(String[] args) {
        User user=new User("ali","ali@gmail.com","1234","Ali","Tashkent");
        String password,newPassword;
        while (true){
           System.out.print("Joriy parolni kiriting: ");
           password=scannerText.nextLine();
           if (password.equals(user.getPassword())){
               System.out.print("Yangi parolni kiriting: ");
               newPassword=scannerText.nextLine();
               if (user.changePassword(newPassword)) {
                   System.out.println("Parol muvaffaqiyatli o'zgartirildi!");
                   break;
               }
           }
           else{
               System.out.println("Joriy parol noto'g'ri kiritildi");
           }
       }
    }
}
