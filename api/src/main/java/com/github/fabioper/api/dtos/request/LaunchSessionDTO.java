package com.github.fabioper.api.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record LaunchSessionDTO(
    @NotNull
    UUID quizId,

    @NotBlank
    UUID authorId
) {
}
