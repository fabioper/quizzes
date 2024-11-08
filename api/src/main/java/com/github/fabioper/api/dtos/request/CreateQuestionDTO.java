package com.github.fabioper.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CreateQuestionDTO(
    @NotBlank
    String statement,

    @NotNull
    List<CreateOptionDTO> options
) {
}
