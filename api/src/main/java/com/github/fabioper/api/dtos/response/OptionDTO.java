package com.github.fabioper.api.dtos.response;

import java.util.UUID;

public record OptionDTO(
    UUID id,
    String text,
    boolean isCorrect
) {
}
