package uz.b25.testing_system.client.ui;

import uz.b25.testing_system.client.Application;
import uz.b25.testing_system.client.util.ScannerUtil;
import uz.b25.testing_system.server.enums.Role;

public class MainUI {

    public static void run() {

        while (true){

            if(Application.sessionUser == null){
                System.out.println("1. Login");
                System.out.println("2. Register");
                System.out.println("0. Exit");
                System.out.print("Enter operation number: ");

                int operation = ScannerUtil.SCANNER_NUM.nextInt();
                if(operation == 0) break;

                switch (operation){
                    case 1 -> AuthUI.login();
                    case 2 -> AuthUI.register();
                }
            }else{
                if (Application.sessionUser.getRole().equals(Role.ADMIN)) {
                    UserUI.adminCabinet();
                }else{
                    UserUI.userCabinet();
                }
            }


        }
    }
}
