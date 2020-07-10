package com.personal.web.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ClientException.class)
    protected ResponseEntity<Object> handlerNotFound(Exception exception, String body, WebRequest webRequest, HttpStatus httpStatus){

        return handleExceptionInternal(exception, body, new HttpHeaders(), httpStatus, webRequest);

    }

}