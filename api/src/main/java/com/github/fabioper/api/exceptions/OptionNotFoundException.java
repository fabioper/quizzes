package com.github.fabioper.api.exceptions;

public class OptionNotFoundException extends RuntimeException {
    public OptionNotFoundException() {
        super("Option not found");
    }
}
