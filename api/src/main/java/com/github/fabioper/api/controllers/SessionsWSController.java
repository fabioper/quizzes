package com.github.fabioper.api.controllers;

import com.github.fabioper.api.dtos.request.AddParticipantDTO;
import com.github.fabioper.api.dtos.response.LiveSessionDTO;
import com.github.fabioper.api.dtos.response.LiveSessionParticipantDTO;
import com.github.fabioper.api.entities.LiveSessionParticipant;
import com.github.fabioper.api.repositories.LiveSessionRepository;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class SessionsWSController {

    private final LiveSessionRepository liveSessionRepository;

    public SessionsWSController(LiveSessionRepository liveSessionRepository) {
        this.liveSessionRepository = liveSessionRepository;
    }

    @MessageMapping("/sessions/{id}/add-participant")
    @SendTo("/topic/sessions/{id}/updated")
    public LiveSessionDTO addParticipant(@DestinationVariable UUID id, AddParticipantDTO dto) {
        var liveSession = this.liveSessionRepository.findById(id).orElseThrow();
        liveSession.addParticipant(new LiveSessionParticipant(dto.id(), dto.nickname()));

        var updatedSession = this.liveSessionRepository.save(liveSession);

        return new LiveSessionDTO(
            updatedSession.getId(),
            updatedSession.getAuthorId(),
            updatedSession.getStatus().getLabel(),
            updatedSession.getParticipants()
                .stream()
                .map(participant -> new LiveSessionParticipantDTO(
                    participant.getId(),
                    participant.getNickname()
                ))
                .toList()
        );
    }
}
