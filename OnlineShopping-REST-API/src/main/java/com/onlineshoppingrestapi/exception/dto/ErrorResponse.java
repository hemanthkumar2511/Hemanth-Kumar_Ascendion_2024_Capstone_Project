package com.onlineshoppingrestapi.exception.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorResponse {
    public int errorCode;
    private String message;
}
