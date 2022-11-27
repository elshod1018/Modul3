package com.company.entity;

import com.company.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    private Integer id;
    private String chatId;
    private Double totalPrice;
    private String orderedAt = String.valueOf(LocalDateTime.now());
    private OrderStatus status = OrderStatus.NOT_COMMITTED;

    public Order(Integer id, String chatId) {
        this.id = id;
        this.chatId = chatId;
    }
}
