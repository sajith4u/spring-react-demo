package com.product.server.api.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Getter
@Setter
public class ErrorMessage {

    private String message;
    private String title;
    private HttpStatus error;
    private int status;
    private String code;
    private String timestamp;

    public ErrorMessage(ApiError error) {
        this.message = error.getMessage();
        this.title = error.getTitle();
        this.error = error.getHttpStatus();
        this.status = error.getHttpStatus().value();
        this.code = error.getCode();
        this.timestamp = new Date().toString();
    }

}
