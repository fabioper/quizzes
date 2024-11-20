package com.github.fabioper.api.exceptions;

public class OptionNotFoundException extends ResourceNotFoundException {
    public OptionNotFoundException() {
        super("Option not found");
    }
}
