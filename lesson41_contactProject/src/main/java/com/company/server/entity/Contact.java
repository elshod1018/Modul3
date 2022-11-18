package com.company.server.entity;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Contact {
    private UUID id;
    private String fullName;
    private String phoneNumber;
}
