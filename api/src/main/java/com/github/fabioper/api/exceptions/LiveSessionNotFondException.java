package com.github.fabioper.api.exceptions;

public class LiveSessionNotFondException extends RuntimeException {
    public LiveSessionNotFondException() {
        super("Live Session not found");
    }
}
