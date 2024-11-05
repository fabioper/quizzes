package com.github.fabioper.api.exceptions;

public class QuizNotFoundException extends RuntimeException {
    public QuizNotFoundException() {
        super("Quiz not found");
    }
}
