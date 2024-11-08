package com.github.fabioper.api.mappers;

import com.github.fabioper.api.dtos.request.CreateOptionDTO;
import com.github.fabioper.api.dtos.request.UpdateOptionDTO;
import com.github.fabioper.api.entities.Option;
import com.github.fabioper.api.entities.Question;
import com.github.fabioper.api.exceptions.OptionNotFoundException;

public class OptionMapper {
    public static Option mapToOptionEntity(CreateOptionDTO optionDto) {
        return new Option(optionDto.text(), optionDto.isCorrect());
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
