package com.github.fabioper.api.mappers;

import com.github.fabioper.api.dtos.*;
import com.github.fabioper.api.entities.Option;
import com.github.fabioper.api.entities.Question;
import com.github.fabioper.api.entities.Quiz;
import com.github.fabioper.api.exceptions.OptionNotFoundException;
import com.github.fabioper.api.exceptions.QuestionNotFoundException;

import java.util.stream.Collectors;

public class Mappers {
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

    public static Quiz mapToQuizEntity(CreateQuizDTO dto) {
        var mappedQuestions = dto.questions().stream().map(Mappers::mapToQuestionEntity).toList();
        return new Quiz(dto.title(), mappedQuestions);
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
}
