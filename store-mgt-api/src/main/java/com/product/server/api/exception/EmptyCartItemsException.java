package com.product.server.api.exception;

public class EmptyCartItemsException extends RuntimeException {
    private ApiError apiError;

    public EmptyCartItemsException(ApiError apiError) {
        super(apiError.getTitle());
        this.apiError = apiError;
    }

    public ApiError getApiError() {
        return apiError;
    }
}
