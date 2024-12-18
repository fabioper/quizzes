package com.github.fabioper.api.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "quizzes")
public class Quiz {
    @Id
    private final UUID id = UUID.randomUUID();

    @Column(nullable = false)
    private final LocalDateTime createdDate = LocalDateTime.now();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", nullable = false)
    private List<Question> questions = new ArrayList<>();

    public Quiz() {
    }

    public Quiz(String title, String description, List<Question> questions) {
        this.description = description;
        this.title = title;
        this.questions = questions;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions.clear();
        this.questions.addAll(questions);
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public Optional<Question> findQuestionBy(UUID questionId) {
        return questions.stream()
            .filter(question -> question.getId().equals(questionId))
            .findFirst();
    }
}
