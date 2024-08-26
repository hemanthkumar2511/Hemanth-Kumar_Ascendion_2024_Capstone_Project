package com.onlineshoppingrestapi.exception.dto;

public class ProductNotFoundException extends Exception{
    private String prodCode;

    public ProductNotFoundException() {
        super();
    }

    public ProductNotFoundException(String prodCode) {
        this.prodCode = prodCode;
    }

    public String toString() {
        return "Product Not Found !!!";
    }
}
