package com.company.service;

import com.company.db.Database;
import com.company.entity.Order;

public class OrderService {

    public static Order getOrderById(Integer orderId) {
        return Database.ORDER_LIST.stream()
                .filter(order -> order.getId().equals(orderId))
                .findFirst().orElse(null);
    }
}
