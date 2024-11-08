package com.github.fabioper.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record UpdateQuestionDTO(
    UUID id,

    @NotBlank
    String statement,

    @NotNull
    List<UpdateOptionDTO> options
) {
}
