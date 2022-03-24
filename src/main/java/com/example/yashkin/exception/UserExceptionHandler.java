package com.example.yashkin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(IOException e) {
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo("ioe " + e.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(NotFoundException ex) {
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo("nfe " + ex.getMessage());

        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(Exception ex) {
        UserIncorrectData data = new UserIncorrectData();
        data.setInfo("exept " + ex.getMessage());

        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
}
