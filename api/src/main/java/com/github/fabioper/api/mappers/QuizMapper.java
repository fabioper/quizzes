package com.github.fabioper.api.mappers;

import com.github.fabioper.api.dtos.request.CreateQuizDTO;
import com.github.fabioper.api.dtos.request.UpdateQuizDTO;
import com.github.fabioper.api.dtos.response.OptionDTO;
import com.github.fabioper.api.dtos.response.QuestionDTO;
import com.github.fabioper.api.dtos.response.QuizDTO;
import com.github.fabioper.api.dtos.response.QuizListDTO;
import com.github.fabioper.api.entities.Option;
import com.github.fabioper.api.entities.Question;
import com.github.fabioper.api.entities.Quiz;

import java.util.stream.Collectors;

public class QuizMapper {
    public static Quiz mapToQuizEntity(CreateQuizDTO dto) {
        var mappedQuestions = dto.questions().stream().map(QuestionMapper::mapToQuestionEntity).toList();
        return new Quiz(dto.title(), dto.description(), mappedQuestions);
    }

    public static Quiz mapToExistingQuizEntity(Quiz existingQuiz, UpdateQuizDTO dto) {
        existingQuiz.setTitle(dto.title());
        existingQuiz.setDescription(dto.description());

        var mappedQuestions = dto.questions().stream()
            .map(questionDto -> QuestionMapper.mapToQuestionEntity(existingQuiz, questionDto))
            .collect(Collectors.toList());

        existingQuiz.setQuestions(mappedQuestions);

        return existingQuiz;
    }

    public static QuizListDTO mapToListQuizzesDTO(Quiz quiz) {
        return new QuizListDTO(
            quiz.getId(),
            quiz.getTitle(),
            quiz.getDescription(),
            quiz.getCreatedDate()
        );
    }

    public static QuizDTO mapToQuizDTO(Quiz quiz) {
        return new QuizDTO(
            quiz.getId(),
            quiz.getTitle(),
            quiz.getDescription(),
            quiz.getCreatedDate(),
            quiz.getQuestions().stream().map(QuizMapper::mapToQuestionDTO).toList()
        );
    }

    private static QuestionDTO mapToQuestionDTO(Question question) {
        return new QuestionDTO(
            question.getId(),
            question.getStatement(),
            question.getOptions().stream().map(QuizMapper::mapToOptionDTO).toList()
        );
    }

    private static OptionDTO mapToOptionDTO(Option option) {
        return new OptionDTO(
            option.getId(),
            option.getText(),
            option.isCorrect()
        );
    }
}
