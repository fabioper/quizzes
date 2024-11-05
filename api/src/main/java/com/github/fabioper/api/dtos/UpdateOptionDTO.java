package com.github.fabioper.api.dtos;

import java.util.UUID;

public record UpdateOptionDTO(
    UUID id,
    String text,
    boolean isCorrect
) {
}
