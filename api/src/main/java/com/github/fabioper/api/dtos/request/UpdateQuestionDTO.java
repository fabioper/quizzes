package com.github.fabioper.api.dtos.request;

import java.util.List;
import java.util.UUID;

public record UpdateQuestionDTO(
    UUID id,
    String statement,
    List<UpdateOptionDTO> options
) {
}
