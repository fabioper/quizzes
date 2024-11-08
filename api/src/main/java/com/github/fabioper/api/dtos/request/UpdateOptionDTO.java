package com.github.fabioper.api.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UpdateOptionDTO(
    UUID id,

    @NotBlank
    String text,

    boolean isCorrect
) {
}
