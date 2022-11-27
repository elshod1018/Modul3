package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BasketDetail {
    private String chatId;
    public Integer productId;
    public Integer quantity;
}
