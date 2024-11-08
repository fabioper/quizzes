package com.github.fabioper.api.dtos.response;

import java.util.List;
import java.util.UUID;

public record QuestionDTO(
    UUID id,
    String statement,
    List<OptionDTO> options
) {
}
