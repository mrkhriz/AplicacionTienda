package com.personal.web.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;

import javax.xml.ws.http.HTTPException;

public class ClientException extends RuntimeException {

    @JsonProperty("status")
    private HttpStatus status;

    public ClientException() {
        super();
    }

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClientException(Throwable cause) {
        super(cause);
    }

    public ClientException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public ClientException(HttpStatus httpStatus, String message){
        super(message);
        this.status = status;
    }

}
