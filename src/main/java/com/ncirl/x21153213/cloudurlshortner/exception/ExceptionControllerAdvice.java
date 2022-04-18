package com.ncirl.x21153213.cloudurlshortner.exception;

import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
/*
@author : x21153213niharika
* This is an Exception class for controlling the exception in the application
@used: exceptionhandlers are defined here to control if happened
@calling classes : UrlServiec, ClientService and test classes
* */
public class ExceptionControllerAdvice {

    private final String SHORT_URL_NOT_FOUND_ERROR = "Short URL Code does not existed, might have been cleaned up";
    private final String API_KEY_NOT_FOUND_ERROR = "Invalid API Key, not related to the client";
    private final String Client_Id_NOT_FOUND_ERROR = "Invalid Client Id, not related to the client";


    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<String> shortUrlNotFound(
            EntityNotFoundException entityNotFoundException) {
        log.error(SHORT_URL_NOT_FOUND_ERROR, entityNotFoundException);

        return new ResponseEntity(SHORT_URL_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {InvalidApiKeyException.class})
    protected ResponseEntity<String> apiKeyNotFound(
            InvalidApiKeyException invalidApiKeyException) {
        log.error(API_KEY_NOT_FOUND_ERROR, invalidApiKeyException);
        return new ResponseEntity(API_KEY_NOT_FOUND_ERROR, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = {ClientIdNotFoundException.class})
    protected ResponseEntity<String> clientIdNotFound(
            ClientIdNotFoundException clientIdNotFoundException) {
        log.error(Client_Id_NOT_FOUND_ERROR, clientIdNotFoundException);
        return new ResponseEntity(Client_Id_NOT_FOUND_ERROR, HttpStatus.BAD_REQUEST);
    }
}