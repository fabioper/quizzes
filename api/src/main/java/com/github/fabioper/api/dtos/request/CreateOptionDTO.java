package com.github.fabioper.api.dtos.request;

public record CreateOptionDTO(
    String text,
    boolean isCorrect
) {
}
