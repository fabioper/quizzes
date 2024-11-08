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
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", nullable = false)
    private List<Question> questions = new ArrayList<>();

    public Quiz() {
    }

    public Quiz(String title, String description, List<Question> questions) {
        this.id = UUID.randomUUID();
        this.description = description;
        this.title = title;
        this.questions = questions;
        this.createdDate = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setQuestions(List<Question> questions) {
        this.questions.clear();
        this.questions.addAll(questions);
    }

    public Optional<Question> findQuestionBy(UUID questionId) {
        return questions.stream()
            .filter(question -> question.getId().equals(questionId))
            .findFirst();
    }
}
