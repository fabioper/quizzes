package com.github.fabioper.api.services;

import com.github.fabioper.api.dtos.request.AddParticipantDTO;
import com.github.fabioper.api.dtos.request.LaunchSessionDTO;
import com.github.fabioper.api.dtos.response.LiveSessionDTO;
import com.github.fabioper.api.dtos.response.LiveSessionParticipantDTO;
import com.github.fabioper.api.entities.LiveSession;
import com.github.fabioper.api.exceptions.LiveSessionNotFondException;
import com.github.fabioper.api.exceptions.QuizNotFoundException;
import com.github.fabioper.api.mappers.LiveSessionMapper;
import com.github.fabioper.api.repositories.LiveSessionRepository;
import com.github.fabioper.api.repositories.QuizRepository;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LiveSessionService {
    private final QuizRepository quizRepository;
    private final LiveSessionRepository liveSessionRepository;
    private final SimpMessagingTemplate messagingTemplate;

    public LiveSessionService(
        QuizRepository quizRepository,
        LiveSessionRepository liveSessionRepository,
        SimpMessagingTemplate messagingTemplate
    ) {
        this.quizRepository = quizRepository;
        this.liveSessionRepository = liveSessionRepository;
        this.messagingTemplate = messagingTemplate;
    }

    public LiveSessionDTO launchSession(LaunchSessionDTO dto) {
        var quiz = quizRepository
            .findById(dto.quizId())
            .orElseThrow(QuizNotFoundException::new);

        var liveSession = new LiveSession(dto.authorId(), quiz);

        var savedLiveSession = liveSessionRepository.save(liveSession);
        return LiveSessionMapper.toDTO(savedLiveSession);
    }

    public LiveSessionDTO findById(UUID id) {
        return liveSessionRepository.findById(id)
            .map(LiveSessionMapper::toDTO)
            .orElseThrow(LiveSessionNotFondException::new);
    }

    public LiveSessionParticipantDTO addParticipant(UUID id, AddParticipantDTO dto) {
        var liveSession = this.liveSessionRepository
            .findById(id)
            .orElseThrow(LiveSessionNotFondException::new);

        var participant = liveSession.addParticipant(dto.id(), dto.nickname());
        var updatedSession = this.liveSessionRepository.save(liveSession);

        var topicDestination = "/topic/sessions/" + id + "/updated";
        var updatedSessionDTO = LiveSessionMapper.toDTO(updatedSession);
        this.messagingTemplate.convertAndSend(topicDestination, updatedSessionDTO);

        return LiveSessionMapper.toDTO(participant);
    }
}
