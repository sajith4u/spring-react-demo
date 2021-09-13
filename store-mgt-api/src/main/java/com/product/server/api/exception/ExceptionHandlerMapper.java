package com.product.server.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerMapper {

    @ExceptionHandler(EmptyCartItemsException.class)
    public ResponseEntity<ErrorMessage> emptyCart(EmptyCartItemsException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(ex.getApiError()));
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorMessage> invalidProduct(ProductNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorMessage(ex.getApiError()));
    }
}
