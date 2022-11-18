package com.company.server.payload;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Result {
    private String message;
    private boolean success;
}
