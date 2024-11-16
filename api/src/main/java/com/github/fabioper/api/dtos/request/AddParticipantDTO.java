package com.github.fabioper.api.dtos.request;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record AddParticipantDTO(
    @NotBlank
    UUID id,
    @NotBlank
    String nickname
) {
}
