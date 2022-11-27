package com.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private String chatId;
    private String firstName;
    private String lastName;
    private String username;
    private String phoneNumber;

    public Customer(String chatId) {
        this.chatId = chatId;
    }
}
