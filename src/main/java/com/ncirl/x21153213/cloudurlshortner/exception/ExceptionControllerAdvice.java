package com.ncirl.x21153213.cloudurlshortner.exception;

import javax.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    private final String SHORT_URL_NOT_FOUND_ERROR = "Short URL Code does not existed, might have been cleaned up";

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<String> shortUrlNotFound(
            EntityNotFoundException entityNotFoundException) {
        log.error(SHORT_URL_NOT_FOUND_ERROR, entityNotFoundException);

        return new ResponseEntity(SHORT_URL_NOT_FOUND_ERROR, HttpStatus.NOT_FOUND);
    }
}