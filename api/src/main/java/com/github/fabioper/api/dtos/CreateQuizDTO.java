package com.github.fabioper.api.dtos;

import java.util.List;

public record CreateQuizDTO(
    String title,
    List<CreateQuestionDTO> questions
) {
}
