package com.github.fabioper.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateQuizDTO(
    @NotBlank
    String title,

    @NotNull
    String description,

    @NotNull
    List<CreateQuestionDTO> questions
) {
}
