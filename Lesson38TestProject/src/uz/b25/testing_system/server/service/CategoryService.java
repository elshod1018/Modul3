package uz.b25.testing_system.server.service;

import uz.b25.testing_system.server.database.Database;
import uz.b25.testing_system.server.entity.Subject;
import uz.b25.testing_system.server.payload.Result;

import java.util.ArrayList;
import java.util.Set;

public class CategoryService {
    public static Result addCategory(String name) {
        if(name == null || name.isBlank()){
            return new Result("Name is required", false);
        }

        name = name.toUpperCase();
        if(Database.QUESTION_MAP.containsKey(name)){
            return new Result("Exist this category", false);
        }

        Database.QUESTION_MAP.put(name, new ArrayList<>());

        return new Result(name+" added.", true);
    }

    public static Set<String> getCategories() {
        return Database.QUESTION_MAP.keySet();
    }

}
