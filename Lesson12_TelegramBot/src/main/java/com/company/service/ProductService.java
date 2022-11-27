package com.company.service;

import com.company.db.Database;
import com.company.entity.Category;
import com.company.entity.Product;
import com.company.files.WorkWithJsonFiles;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    public static String addProduct(Product product) {

        Integer id = Database.PRODUCT_LIST.isEmpty() ? 1 :
                Database.PRODUCT_LIST.get(Database.PRODUCT_LIST.size()-1).getId()+1;

        product.setId(id);
        Database.PRODUCT_LIST.add(product);
        WorkWithJsonFiles.AddProductToJson();

        return product.getName()+" successfully added";
    }

    public static List<Product> getProductListByCategoryId(Integer categoryId) {
        return Database.PRODUCT_LIST.stream()
                .filter(product -> product.getCategoryId().equals(categoryId))
                .collect(Collectors.toList());
    }

    public static Product getProductById(Integer productId) {
        return Database.PRODUCT_LIST.stream()
                .filter(product -> product.getId().equals(productId))
                .findFirst().orElse(null);
    }
}
