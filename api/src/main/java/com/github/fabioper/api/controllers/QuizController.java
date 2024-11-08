package com.github.fabioper.api.controllers;

import com.github.fabioper.api.dtos.request.CreateQuizDTO;
import com.github.fabioper.api.dtos.request.UpdateQuizDTO;
import com.github.fabioper.api.dtos.response.QuizDTO;
import com.github.fabioper.api.dtos.response.QuizListDTO;
import com.github.fabioper.api.services.QuizService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<QuizListDTO> listAll() {
        return quizService.listQuizzes();
    }

    @PostMapping
    public ResponseEntity<QuizDTO> create(@Valid @RequestBody CreateQuizDTO dto) {
        var createdQuiz = quizService.createQuiz(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdQuiz);
    }

    @PutMapping("{id}")
    public QuizDTO update(@PathVariable UUID id, @Valid @RequestBody UpdateQuizDTO dto) {
        return quizService.updateQuiz(id, dto);
    }

    @GetMapping("{id}")
    public QuizDTO findById(@PathVariable UUID id) {
        return quizService.findById(id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable UUID id) {
        quizService.delete(id);
    }
}
