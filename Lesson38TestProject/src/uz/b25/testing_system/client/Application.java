package uz.b25.testing_system.client;

import uz.b25.testing_system.client.ui.MainUI;
import uz.b25.testing_system.server.database.Database;
import uz.b25.testing_system.server.entity.User;
import uz.b25.testing_system.server.enums.Role;

import java.util.ArrayList;

public class Application {

    public static User sessionUser  = null;

    static {
        User admin = new User((long) (Database.USERS.size()+1),
                "admin", "+998901234567", "4567");
        admin.setRole(Role.ADMIN);

        Database.USERS.add(admin);

        User user = new User((long) (Database.USERS.size()+1),
                "user", "+998901112233", "2233");
        Database.USERS.add(user);
        Database.TEST_HISTORY_MAP.put(user, new ArrayList<>());

        Database.QUESTION_MAP.put("MATH", new ArrayList<>());
        Database.QUESTION_MAP.put("ENGLISH", new ArrayList<>());
    }

    public static void main(String[] args) {
        MainUI.run();
    }
}
