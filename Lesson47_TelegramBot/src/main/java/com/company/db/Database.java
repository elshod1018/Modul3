package com.company.db;

import com.company.entity.Category;
import com.company.entity.Product;

import java.util.ArrayList;
import java.util.List;

public interface Database {
    List<Category> CATEGORY_LIST = new ArrayList<>();
    List<Product> PRODUCT_LIST = new ArrayList<>();
}
