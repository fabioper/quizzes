package com.github.fabioper.api.dtos.request;

import java.util.List;

public record CreateQuizDTO(
    String title,
    String description,
    List<CreateQuestionDTO> questions
) {
}
