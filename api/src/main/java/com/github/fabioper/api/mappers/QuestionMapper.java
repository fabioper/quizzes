package com.github.fabioper.api.mappers;

import com.github.fabioper.api.dtos.request.CreateQuestionDTO;
import com.github.fabioper.api.dtos.request.UpdateQuestionDTO;
import com.github.fabioper.api.entities.Option;
import com.github.fabioper.api.entities.Question;
import com.github.fabioper.api.entities.Quiz;
import com.github.fabioper.api.exceptions.QuestionNotFoundException;

import java.util.stream.Collectors;

public class QuestionMapper {
    public static Question mapToQuestionEntity(CreateQuestionDTO questionDto) {
        var mappedOptions = questionDto
            .options()
            .stream()
            .map(OptionMapper::mapToOptionEntity)
            .toList();

        return new Question(questionDto.statement(), mappedOptions);
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
            .map(optionDto -> OptionMapper.mapToOptionEntity(question, optionDto))
            .collect(Collectors.toList());

        question.setOptions(options);

        return question;
    }
}
