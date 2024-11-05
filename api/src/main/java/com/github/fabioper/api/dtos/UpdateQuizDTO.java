package com.github.fabioper.api.dtos;

import java.util.List;

public record UpdateQuizDTO(
    String title,
    List<UpdateQuestionDTO> questions
) {
}
