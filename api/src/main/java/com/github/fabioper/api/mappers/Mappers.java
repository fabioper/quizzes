package com.github.fabioper.api.mappers;

import com.github.fabioper.api.dtos.request.*;
import com.github.fabioper.api.dtos.response.OptionDTO;
import com.github.fabioper.api.dtos.response.QuestionDTO;
import com.github.fabioper.api.dtos.response.QuizDTO;
import com.github.fabioper.api.dtos.response.QuizListDTO;
import com.github.fabioper.api.entities.Option;
import com.github.fabioper.api.entities.Question;
import com.github.fabioper.api.entities.Quiz;
import com.github.fabioper.api.exceptions.OptionNotFoundException;
import com.github.fabioper.api.exceptions.QuestionNotFoundException;

import java.util.stream.Collectors;

public class Mappers {
    public static Quiz mapToQuizEntity(CreateQuizDTO dto) {
        var mappedQuestions = dto.questions().stream().map(Mappers::mapToQuestionEntity).toList();
        return new Quiz(dto.title(), dto.description(), mappedQuestions);
    }

    public static Quiz mapToExistingQuizEntity(Quiz existingQuiz, UpdateQuizDTO dto) {
        existingQuiz.setTitle(dto.title());
        existingQuiz.setDescription(dto.description());

        var mappedQuestions = dto.questions().stream()
            .map(questionDto -> mapToQuestionEntity(existingQuiz, questionDto))
            .collect(Collectors.toList());

        existingQuiz.setQuestions(mappedQuestions);

        return existingQuiz;
    }

    public static Question mapToQuestionEntity(CreateQuestionDTO questionDto) {
        var mappedOptions = questionDto
            .options()
            .stream()
            .map(Mappers::mapToOptionEntity)
            .toList();

        return new Question(questionDto.statement(), mappedOptions);
    }

    public static Option mapToOptionEntity(CreateOptionDTO optionDto) {
        return new Option(optionDto.text(), optionDto.isCorrect());
    }

    public static Question mapToQuestionEntity(Quiz quiz, UpdateQuestionDTO questionDto) {
        if (questionDto.id() == null) {
            return mapToNewQuestionEntity(questionDto);
        }

        return quiz.findQuestionBy(questionDto.id())
            .map(question -> mapToExistingQuestionEntity(question, questionDto))
            .orElseThrow(QuestionNotFoundException::new);
    }

    public static Question mapToNewQuestionEntity(UpdateQuestionDTO questionDto) {
        return new Question(
            questionDto.statement(),
            questionDto.options()
                .stream()
                .map(optionDto -> new Option(optionDto.text(), optionDto.isCorrect()))
                .collect(Collectors.toList())
        );
    }

    public static Question mapToExistingQuestionEntity(Question question, UpdateQuestionDTO questionDto) {
        question.setStatement(questionDto.statement());

        var options = questionDto.options().stream()
            .map(optionDto -> mapToOptionEntity(question, optionDto))
            .collect(Collectors.toList());

        question.setOptions(options);

        return question;
    }

    public static Option mapToOptionEntity(Question question, UpdateOptionDTO optionDto) {
        if (optionDto.id() == null) {
            return new Option(optionDto.text(), optionDto.isCorrect());
        }

        return question.findOptionBy(optionDto.id())
            .map(option -> mapToExistingOptionEntity(option, optionDto))
            .orElseThrow(OptionNotFoundException::new);
    }

    public static Option mapToExistingOptionEntity(Option option, UpdateOptionDTO optionDto) {
        option.setText(optionDto.text());
        option.setCorrect(optionDto.isCorrect());
        return option;
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
            quiz.getQuestions().stream().map(Mappers::mapToQuestionDTO).toList()
        );
    }

    private static QuestionDTO mapToQuestionDTO(Question question) {
        return new QuestionDTO(
            question.getId(),
            question.getStatement(),
            question.getOptions().stream().map(Mappers::mapToOptionDTO).toList()
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
