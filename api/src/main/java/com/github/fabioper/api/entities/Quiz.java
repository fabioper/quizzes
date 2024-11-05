package com.github.fabioper.api.entities;

import jakarta.persistence.*;

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

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", nullable = false)
    private List<Question> questions = new ArrayList<>();

    public Quiz() {
    }

    public Quiz(String title, List<Question> questions) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.questions = questions;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setTitle(String title) {
        this.title = title;
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
