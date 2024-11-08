package com.github.fabioper.api.services;

import com.github.fabioper.api.dtos.request.CreateQuizDTO;
import com.github.fabioper.api.dtos.request.UpdateQuizDTO;
import com.github.fabioper.api.dtos.response.QuizDTO;
import com.github.fabioper.api.dtos.response.QuizListDTO;
import com.github.fabioper.api.exceptions.QuizNotFoundException;
import com.github.fabioper.api.mappers.Mappers;
import com.github.fabioper.api.repositories.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public QuizDTO createQuiz(CreateQuizDTO dto) {
        var newQuiz = Mappers.mapToQuizEntity(dto);
        var savedQuiz = quizRepository.saveAndFlush(newQuiz);
        return Mappers.mapToQuizDTO(savedQuiz);
    }

    public QuizDTO updateQuiz(UUID id, UpdateQuizDTO dto) {
        var quizToBeUpdated = quizRepository
            .findById(id)
            .orElseThrow(QuizNotFoundException::new);

        var mappedQuiz = Mappers.mapToExistingQuizEntity(quizToBeUpdated, dto);
        var updatedQuiz = quizRepository.saveAndFlush(mappedQuiz);
        return Mappers.mapToQuizDTO(updatedQuiz);
    }

    public List<QuizListDTO> listQuizzes() {
        var quizzes = quizRepository.findAll();
        return quizzes.stream().map(Mappers::mapToListQuizzesDTO).toList();
    }

    public QuizDTO findById(UUID id) {
        return quizRepository.findById(id)
            .map(Mappers::mapToQuizDTO)
            .orElseThrow(QuizNotFoundException::new);
    }

    public void delete(UUID id) {
        var quizToBeDeleted = quizRepository
            .findById(id)
            .orElseThrow(QuizNotFoundException::new);

        quizRepository.delete(quizToBeDeleted);
    }
}
