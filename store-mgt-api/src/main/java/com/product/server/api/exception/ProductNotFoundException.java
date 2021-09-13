package com.product.server.api.exception;

public class ProductNotFoundException extends RuntimeException {
    private ApiError apiError;

    public ProductNotFoundException(ApiError apiError) {
        super(apiError.getTitle());
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }
}
