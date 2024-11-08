package com.github.fabioper.api.dtos.request;

import java.util.UUID;

public record UpdateOptionDTO(
    UUID id,
    String text,
    boolean isCorrect
) {
}
