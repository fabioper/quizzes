package com.github.fabioper.api.repositories;

import com.github.fabioper.api.entities.LiveSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LiveSessionRepository extends JpaRepository<LiveSession, UUID> {
}
