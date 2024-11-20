package com.github.fabioper.api.exceptions;

public class LiveSessionNotFondException extends ResourceNotFoundException {
    public LiveSessionNotFondException() {
        super("Live Session not found");
    }
}
