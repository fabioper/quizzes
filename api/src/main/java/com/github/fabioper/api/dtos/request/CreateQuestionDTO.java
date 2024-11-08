package com.github.fabioper.api.dtos.request;

import java.util.List;

public record CreateQuestionDTO(
    String statement,
    List<CreateOptionDTO> options
) {
}
