package com.company.service;

import com.company.db.Database;
import com.company.entity.Category;

import java.util.Optional;

public class CategoryService {
    public static String addCategory(String name) {
        try{
            if(name == null || name.isBlank()){
                throw new RuntimeException("Category name is required");
            }

            Optional<Category> categoryOptional = Database.CATEGORY_LIST.stream()
                    .filter(category -> category.getName().equalsIgnoreCase(name))
                    .findFirst();

            if(categoryOptional.isPresent()) {
                return "This category already exists";
            }

            Integer id = Database.CATEGORY_LIST.isEmpty() ? 1 :
                    Database.CATEGORY_LIST.get(Database.CATEGORY_LIST.size()-1).getId()+1;

            Category category = new Category(id, name);
            Database.CATEGORY_LIST.add(category);

        }catch (RuntimeException e){
            return e.getMessage();
        }

        return String.format("New category (%s) added.", name.trim());
    }

    public static String editCategory(String newCategoryName, Integer categoryId) {

        try{
            if(newCategoryName == null || newCategoryName.isBlank()){
                throw new RuntimeException("Category name is required");
            }

            Optional<Category> categoryOptional = Database.CATEGORY_LIST.stream()
                    .filter(category -> category.getName().equalsIgnoreCase(newCategoryName))
                    .findFirst();

            if(categoryOptional.isPresent() && !categoryOptional.get().getId().equals(categoryId)) {
                return "This category already exists";
            }

            Category category = Database.CATEGORY_LIST.stream()
                    .filter(category1 -> category1.getId().equals(categoryId))
                    .findFirst().orElse(null);

            category.setName(newCategoryName);

        }catch (RuntimeException e){
            return e.getMessage();
        }

        return "Category edited.";
    }
}
