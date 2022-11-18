package uz.b25.testing_system.server.database;

import uz.b25.testing_system.server.entity.Question;
import uz.b25.testing_system.server.entity.Subject;
import uz.b25.testing_system.server.entity.TestHistory;
import uz.b25.testing_system.server.entity.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface Database {
    List<User> USERS = new ArrayList<>();
//    List<Subject> SUBJECTS = new ArrayList<>();

    Map<String,ArrayList<Question>> QUESTION_MAP = new HashMap<>(5);

    Map<User, List<TestHistory>> TEST_HISTORY_MAP = new HashMap<>();

}
