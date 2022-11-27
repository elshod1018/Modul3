package com.company.entity;

import com.company.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    private Integer id;
    private Integer categoryId;
    private String name;
    private Double price;
    private String description;
    private String imageUrl;

    public String detailInfo() {
        Category category = CategoryService.getCategoryById(categoryId);

        StringBuilder sb = new StringBuilder();
        sb.append("Category: ").append(category.getName()).append("\n");
        sb.append("Product: ").append(name).append("\n");
        sb.append("Price: ").append(price).append("\n");
        sb.append("Description: ").append(description).append("\n");

        return sb.toString();
    }
}
