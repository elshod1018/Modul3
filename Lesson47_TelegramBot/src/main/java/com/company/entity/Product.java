package com.company.entity;

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
    private Integer quantity;
    private String description;
    private String imageUrl;
}
