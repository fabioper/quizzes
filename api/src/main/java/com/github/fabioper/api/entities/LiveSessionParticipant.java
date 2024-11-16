package com.github.fabioper.api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "participants")
public class LiveSessionParticipant {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String nickname;

    public LiveSessionParticipant() {
    }

    public LiveSessionParticipant(UUID id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public UUID getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
