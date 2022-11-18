package uz.b25.testing_system.server.service;

import uz.b25.testing_system.client.Application;
import uz.b25.testing_system.server.database.Database;
import uz.b25.testing_system.server.entity.User;
import uz.b25.testing_system.server.payload.Result;

public class UserService {
    public static User getUserByPhoneNumber(String phoneNumber) {

        for (User user : Database.USERS) {
            if(user.getPhoneNumber().equals(phoneNumber)){
                return user;
            }
        }
        return null;
    }
    public static Result addBalance(double addBalance){
        Application.sessionUser.setBalance(Application.sessionUser.getBalance()+addBalance);
        return new Result("Muvaffaqiyatli qo'shild sizning balansingiz: "
                +Application.sessionUser.getBalance(),true);
    }
}
