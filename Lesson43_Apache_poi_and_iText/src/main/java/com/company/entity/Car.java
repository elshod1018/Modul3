package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
    private UUID id;
    private String model;
    private String number;
    private double price;
//    private File imageFile;
    private String imageUrl;
}
