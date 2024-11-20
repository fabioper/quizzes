package com.github.fabioper.api.exceptions;

public class QuestionNotFoundException extends ResourceNotFoundException {
    public QuestionNotFoundException() {
        super("Question not found");
    }
}
