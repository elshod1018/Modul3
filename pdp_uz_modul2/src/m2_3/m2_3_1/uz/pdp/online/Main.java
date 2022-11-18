package m2_3.m2_3_1.uz.pdp.online;

import m2_3.m2_3_1.uz.pdp.online.model.User;

public class Main {
    public static void main(String[] args) {
        User[] users=new User[5];
        users[0]=new User("ali","ali@gmail.com","1111","Ali","Tashkent");
        users[1]=new User("vali","vali@gmail.com","2222","Vali","Tashkent");
        users[2]=new User("soli","soli@gmail.com","3333","Soli","Tashkent");
        users[3]=new User("g'ani","g'ani@gmail.com","4444","G'ani","Tashkent");
        users[4]=new User("guli","guli@gmail.com","5555","Guli","Tashkent");
        for (User user : users) {
            System.out.println(user);
        }
    }
}
