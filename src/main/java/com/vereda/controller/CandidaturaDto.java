package com.vereda.controller;

import com.vereda.model.enums.StatusCandidatura;
import jakarta.validation.constraints.NotNull;

public record CandidaturaDto(
        @NotNull Long trabalhadorId,
        @NotNull Long idVaga,
        StatusCandidatura status
) {}

