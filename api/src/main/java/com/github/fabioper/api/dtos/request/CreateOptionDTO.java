package com.github.fabioper.api.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record CreateOptionDTO(
    @NotBlank
    String text,

    boolean isCorrect
) {
}
