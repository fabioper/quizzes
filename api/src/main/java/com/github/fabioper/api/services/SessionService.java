package com.github.fabioper.api.services;

import com.github.fabioper.api.dtos.request.LaunchSessionDTO;
import com.github.fabioper.api.dtos.response.LiveSessionDTO;
import com.github.fabioper.api.dtos.response.LiveSessionParticipantDTO;
import com.github.fabioper.api.entities.LiveSession;
import com.github.fabioper.api.exceptions.QuizNotFoundException;
import com.github.fabioper.api.repositories.LiveSessionRepository;
import com.github.fabioper.api.repositories.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionService {
    private final QuizRepository quizRepository;
    private final LiveSessionRepository liveSessionRepository;

    public SessionService(QuizRepository quizRepository, LiveSessionRepository liveSessionRepository) {
        this.quizRepository = quizRepository;
        this.liveSessionRepository = liveSessionRepository;
    }

    private static LiveSessionDTO mapToLiveSessionDTO(LiveSession entity) {
        return new LiveSessionDTO(
            entity.getId(),
            entity.getAuthorId(),
            entity.getStatus().getLabel(),
            entity.getParticipants()
                .stream()
                .map(participant -> new LiveSessionParticipantDTO(
                    participant.getId(),
                    participant.getNickname()
                ))
                .toList()
        );
    }

    public LiveSessionDTO launchSession(LaunchSessionDTO dto) {
        var quiz = quizRepository.findById(dto.quizId())
            .orElseThrow(QuizNotFoundException::new);

        var liveSession = new LiveSession(dto.authorId(), quiz);

        var savedLiveSession = liveSessionRepository.save(liveSession);
        return mapToLiveSessionDTO(savedLiveSession);
    }

    public LiveSessionDTO findById(UUID id) {
        return liveSessionRepository.findById(id)
            .map(SessionService::mapToLiveSessionDTO)
            .orElseThrow();
    }
}
