package com.github.fabioper.api.dtos.request;

import java.util.List;

public record UpdateQuizDTO(
    String title,
    String description,
    List<UpdateQuestionDTO> questions
) {
}
