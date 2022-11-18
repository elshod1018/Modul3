package uz.b25.testing_system.client.ui;

import uz.b25.testing_system.client.util.ScannerUtil;
import uz.b25.testing_system.server.database.Database;
import uz.b25.testing_system.server.entity.Question;
import uz.b25.testing_system.server.payload.Result;
import uz.b25.testing_system.server.service.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryUI {
    public static void addCategory() {
        System.out.println("Enter category name");
        String name = ScannerUtil.SCANNER_STR.nextLine();
        if (Database.QUESTION_MAP.containsKey(name)){
            System.out.println("This category already exists");
            return;
        }
        Result result = CategoryService.addCategory(name);
        System.out.println(result);
    }

    public static boolean showCategories() {
        Set<String> categories = CategoryService.getCategories();
        if (categories.isEmpty()) {
            System.out.println("No categories");
            return false;
        }

        categories.forEach(System.out::println);
        return true;
    }
}
