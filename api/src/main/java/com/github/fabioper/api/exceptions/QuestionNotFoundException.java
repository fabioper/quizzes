package com.github.fabioper.api.exceptions;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException() {
        super("Question not found");
    }
}
