package com.github.fabioper.api.controllers;

import com.github.fabioper.api.dtos.request.AddParticipantDTO;
import com.github.fabioper.api.dtos.request.LaunchSessionDTO;
import com.github.fabioper.api.dtos.response.LiveSessionDTO;
import com.github.fabioper.api.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/sessions")
public class SessionsController {
    private final SessionService sessionService;

    public SessionsController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PostMapping
    public ResponseEntity<LiveSessionDTO> launchSession(@RequestBody LaunchSessionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.launchSession(dto));
    }

    @GetMapping("{id}")
    public LiveSessionDTO findById(@PathVariable UUID id) {
        return sessionService.findById(id);
    }

    @PostMapping("{id}/participants")
    public ResponseEntity<Object> addParticipant(@PathVariable UUID id, @RequestBody AddParticipantDTO dto) {
        sessionService.addParticipant(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
