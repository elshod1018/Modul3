package com.company.service;

import com.company.db.Database;
import com.company.entity.BasketDetail;

import java.util.List;
import java.util.stream.Collectors;

public class BasketDetailService {
    public static List<BasketDetail> getBasket(String chatId) {
        return Database.BASKET_DETAIL_LIST.stream()
                .filter(basketDetail -> basketDetail.getChatId().equals(chatId))
                .collect(Collectors.toList());
    }
}
