package uz.b25.testing_system.client.ui;

import uz.b25.testing_system.client.util.ScannerUtil;
import uz.b25.testing_system.server.payload.Result;
import uz.b25.testing_system.server.payload.UserDTO;
import uz.b25.testing_system.server.service.AuthService;

public class AuthUI {

    public static void register() {
        System.out.println("Enter full name");
        String fullName = ScannerUtil.SCANNER_STR.nextLine();

        System.out.println("Enter phone number (+998XXYYYYYYY)");
        String phoneNumber = ScannerUtil.SCANNER_STR.nextLine();

        System.out.println("Enter password");
        String password = ScannerUtil.SCANNER_STR.nextLine();

        System.out.println("Enter password again");
        String confirmPassword = ScannerUtil.SCANNER_STR.nextLine();

        UserDTO userDTO = new UserDTO();
        userDTO.setFullName(fullName);
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setPassword(password);
        userDTO.setConfirmPassword(confirmPassword);

        Result response = AuthService.register(userDTO);
        System.out.println(response);
    }

    public static void login() {
        System.out.println("Enter phone number (+998XXYYYYYYY)");
        String phoneNumber = ScannerUtil.SCANNER_STR.nextLine();

        System.out.println("Enter password");
        String password = ScannerUtil.SCANNER_STR.nextLine();

        UserDTO userDTO = new UserDTO();
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setPassword(password);

        Result response = AuthService.login(userDTO);
        System.out.println(response);
    }
}
