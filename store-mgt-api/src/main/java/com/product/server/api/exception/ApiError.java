package com.product.server.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ApiError {
    EMPTY_CART_ITEMS("Empty Cart Items", "ST001", "Please Add Items to Cart", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND("Product Not Available", "ST002", "This product Not Available", HttpStatus.BAD_REQUEST);

    private String title;
    private String code;
    private String message;
    private HttpStatus httpStatus;

    ApiError(String title, String code, String message, HttpStatus httpStatus) {
        this.title = title;
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
