package com.github.fabioper.api.controllers;

import com.github.fabioper.api.dtos.request.AddParticipantDTO;
import com.github.fabioper.api.dtos.request.LaunchSessionDTO;
import com.github.fabioper.api.dtos.response.LiveSessionDTO;
import com.github.fabioper.api.services.LiveSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/sessions")
public class LiveSessionController {
    private final LiveSessionService liveSessionService;

    public LiveSessionController(LiveSessionService liveSessionService) {
        this.liveSessionService = liveSessionService;
    }

    @PostMapping
    public ResponseEntity<LiveSessionDTO> launchSession(@RequestBody LaunchSessionDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(liveSessionService.launchSession(dto));
    }

    @GetMapping("{id}")
    public LiveSessionDTO findById(@PathVariable UUID id) {
        return liveSessionService.findById(id);
    }

    @PostMapping("{id}/participants")
    public ResponseEntity<Object> addParticipant(@PathVariable UUID id, @RequestBody AddParticipantDTO dto) {
        var newParticipant = liveSessionService.addParticipant(id, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newParticipant);
    }
}
