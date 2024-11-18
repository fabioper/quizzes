package com.github.fabioper.api.mappers;

import com.github.fabioper.api.dtos.response.LiveSessionDTO;
import com.github.fabioper.api.dtos.response.LiveSessionParticipantDTO;
import com.github.fabioper.api.entities.LiveSession;
import com.github.fabioper.api.entities.LiveSessionParticipant;

public class LiveSessionMapper {
    public static LiveSessionDTO toDTO(LiveSession entity) {
        return new LiveSessionDTO(
            entity.getId(),
            entity.getAuthorId(),
            entity.getStatus().getLabel(),
            entity.getParticipants()
                .stream()
                .map(LiveSessionMapper::toDTO)
                .toList()
        );
    }

    public static LiveSessionParticipantDTO toDTO(LiveSessionParticipant entity) {
        return new LiveSessionParticipantDTO(
            entity.getId(),
            entity.getNickname(),
            entity.getScore()
        );
    }
}
