package com.github.fabioper.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ QuizNotFoundException.class, QuestionNotFoundException.class, OptionNotFoundException.class })
    public ResponseEntity<Object> handleQuizNotFoundException(RuntimeException exception) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(exception.getMessage());
    }
}
