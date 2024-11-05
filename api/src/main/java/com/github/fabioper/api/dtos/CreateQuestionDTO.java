package com.github.fabioper.api.dtos;

import java.util.List;

public record CreateQuestionDTO(
    String statement,
    List<CreateOptionDTO> options
) {
}
