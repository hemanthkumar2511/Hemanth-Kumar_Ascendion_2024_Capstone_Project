package com.onlineshoppingrestapi.exception.dto;

public class CustomerNotFoundException extends Exception{
    private String custCode;

    public CustomerNotFoundException() {
        super();
    }

    public CustomerNotFoundException(String custCode) {
        this.custCode = custCode;
    }

    public String toString() {
        return "Customer Not Found !!!";
    }
}
