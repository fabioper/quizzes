package com.github.fabioper.api.exceptions;

public class QuizNotFoundException extends ResourceNotFoundException {
    public QuizNotFoundException() {
        super("Quiz not found");
    }
}
