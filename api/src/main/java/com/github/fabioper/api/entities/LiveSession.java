package com.github.fabioper.api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "live-sessions")
public class LiveSession {
    @Id
    private final UUID id = UUID.randomUUID();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "live_session_id", nullable = false)
    private final List<LiveSessionParticipant> participants = new ArrayList<>();

    @Column(nullable = false)
    private LiveSessionStatus status = LiveSessionStatus.PREPARING;

    @Column(nullable = false)
    private UUID authorId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    public LiveSession() {
    }

    public LiveSession(UUID authorId, Quiz quiz) {
        this.quiz = quiz;
        this.authorId = authorId;
    }

    public UUID getId() {
        return id;
    }

    public LiveSessionStatus getStatus() {
        return status;
    }

    public void setStatus(LiveSessionStatus status) {
        this.status = status;
    }

    public List<LiveSessionParticipant> getParticipants() {
        return participants;
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void addParticipant(LiveSessionParticipant participant) {
        this.participants.add(participant);
    }
}
