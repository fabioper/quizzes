package com.github.fabioper.api.dtos.response;

import java.util.UUID;

public record LiveSessionParticipantDTO(
    UUID id,
    String nickname
) {
}
