package com.github.fabioper.api.dtos.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record QuizDTO(
    UUID id,
    String title,
    String description,
    LocalDateTime createdDate,
    List<QuestionDTO> questions
) {
}
