package com.github.fabioper.api.controllers;

import com.github.fabioper.api.dtos.CreateQuizDTO;
import com.github.fabioper.api.dtos.UpdateQuizDTO;
import com.github.fabioper.api.entities.Quiz;
import com.github.fabioper.api.services.QuizService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public List<Quiz> listAll() {
        return quizService.listQuizzes();
    }

    @PostMapping
    public Quiz create(@RequestBody CreateQuizDTO dto) {
        return quizService.createQuiz(dto);
    }

    @PutMapping("{id}")
    public Quiz update(@PathVariable UUID id, @RequestBody UpdateQuizDTO dto) {
        return quizService.updateQuiz(id, dto);
    }

    @GetMapping("{id}")
    public Quiz findById(@PathVariable UUID id) {
        return quizService.findById(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        quizService.delete(id);
    }
}