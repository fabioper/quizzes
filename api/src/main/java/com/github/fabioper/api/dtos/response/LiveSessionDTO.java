package com.github.fabioper.api.dtos.response;

import java.util.List;
import java.util.UUID;

public record LiveSessionDTO(
    UUID id,
    UUID authorId,
    String status,
    List<LiveSessionParticipantDTO> participants
) {
}
