package com.onlineshoppingrestapi.exception.controller;

import com.onlineshoppingrestapi.exception.dto.CustomerNotFoundException;
import com.onlineshoppingrestapi.exception.dto.ErrorResponse;
import com.onlineshoppingrestapi.exception.dto.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleProductNotFound(ProductNotFoundException e){
        return new ErrorResponse(400, e.toString());
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCustomerNotFound(CustomerNotFoundException e){
        return new ErrorResponse(400, e.toString());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleGenericException(Exception ex) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An unexpected error occurred: " + ex.getMessage());
    }

}
