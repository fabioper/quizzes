package com.github.fabioper.api.dtos.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record QuizListDTO(
    UUID id,
    String title,
    String description,
    LocalDateTime createdDate
) {
}
