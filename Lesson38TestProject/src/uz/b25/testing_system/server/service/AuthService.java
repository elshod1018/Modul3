package uz.b25.testing_system.server.service;

import uz.b25.testing_system.client.Application;
import uz.b25.testing_system.server.database.Database;
import uz.b25.testing_system.server.entity.User;
import uz.b25.testing_system.server.payload.Result;
import uz.b25.testing_system.server.payload.UserDTO;

import java.util.ArrayList;

public class AuthService {

    public static Result register(UserDTO userDTO) {
        String check = checkParameters(userDTO);
        if(!check.equals("ok")){
            return new Result(check, false);
        }

        User user = UserService.getUserByPhoneNumber(userDTO.getPhoneNumber());
        if(user != null){
            return new Result("User exist with number "+userDTO.getPhoneNumber(),
                    false);
        }

        user = new User((long) (Database.USERS.size()+1), userDTO.getFullName(),
                userDTO.getPhoneNumber(), userDTO.getPassword());
        Database.USERS.add(user);

        Database.TEST_HISTORY_MAP.put(user, new ArrayList<>());

        return new Result(userDTO.getFullName()+" registered", true);
    }

    private static String checkParameters(UserDTO userDTO) {

        if(userDTO == null){
            return "User doesn't have fields";
        }

        if (userDTO.getFullName()==null || userDTO.getFullName().isBlank()) {
            return "Full name is required";
        }

        String phoneNumber = userDTO.getPhoneNumber();
        if(phoneNumber==null || phoneNumber.isBlank()){
            return "Phone number is required";
        }

        if(!phoneNumber.matches("\\+998\\d{9}")){
            return "Invalid phone number";
        }

        String password = userDTO.getPassword();
        if(password == null || password.isBlank()){
            return "Password is required";
        }

        String confirmPassword = userDTO.getConfirmPassword();
        if(confirmPassword == null || confirmPassword.isBlank()){
            return "Confirm password is required";
        }

        if(!password.equals(confirmPassword)){
            return "Passwords don't match";
        }

        return "ok";
    }

    public static Result login(UserDTO userDTO) {

        userDTO.setFullName("full name");
        userDTO.setConfirmPassword(userDTO.getPassword());

        String check = checkParameters(userDTO);
        if(!check.equals("ok")){
            return new Result(check, false);
        }

        User user = UserService.getUserByPhoneNumber(userDTO.getPhoneNumber());

        if(user == null || !user.getPassword().equals(userDTO.getPassword())){
            return new Result("Wrong phone number or password",
                    false);
        }

        Application.sessionUser = user;

        return new Result( user.getFullName()+" successfully signed.", true);
    }
}
