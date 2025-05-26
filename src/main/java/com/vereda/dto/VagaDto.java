package com.vereda.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VagaDto(
        @NotBlank String titulo,
        @NotBlank String descricao,
        @NotBlank String local,
        @NotNull Boolean ativa,
        @NotNull Long empresaId
) {}
