package com.github.fabioper.api.services;

import com.github.fabioper.api.dtos.CreateQuizDTO;
import com.github.fabioper.api.dtos.UpdateQuizDTO;
import com.github.fabioper.api.entities.Quiz;
import com.github.fabioper.api.repositories.QuizRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.github.fabioper.api.mappers.Mappers.mapToQuestionsEntities;
import static com.github.fabioper.api.mappers.Mappers.mapToQuizEntity;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public void createQuiz(CreateQuizDTO dto) {
        var newQuiz = mapToQuizEntity(dto);
        quizRepository.save(newQuiz);
    }

    public Quiz updateQuiz(UUID id, UpdateQuizDTO dto) {
        var quizToBeUpdated = quizRepository.findById(id).orElseThrow();

        quizToBeUpdated.setTitle(dto.title());

        var mappedQuestions = mapToQuestionsEntities(quizToBeUpdated, dto.questions());
        quizToBeUpdated.setQuestions(mappedQuestions);

        return quizRepository.save(quizToBeUpdated);
    }

    public List<Quiz> listQuizzes() {
        return quizRepository.findAll();
    }
}
