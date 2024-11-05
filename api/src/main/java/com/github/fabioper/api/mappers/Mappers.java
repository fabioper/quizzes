package com.github.fabioper.api.mappers;

import com.github.fabioper.api.dtos.CreateOptionDTO;
import com.github.fabioper.api.dtos.CreateQuestionDTO;
import com.github.fabioper.api.dtos.CreateQuizDTO;
import com.github.fabioper.api.entities.Option;
import com.github.fabioper.api.entities.Question;
import com.github.fabioper.api.entities.Quiz;

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
}
