package com.github.fabioper.api.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String statement;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "question_id", nullable = false)
    private List<Option> options = new ArrayList<>();

    public Question() {
    }

    public Question(String statement, List<Option> options) {
        this.id = UUID.randomUUID();
        this.statement = statement;
        this.options = options;
    }

    public UUID getId() {
        return id;
    }

    public String getStatement() {
        return statement;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options.clear();
        this.options.addAll(options);
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }
}
