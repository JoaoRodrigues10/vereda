package com.vereda.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record AvaliacaoDto(Long candidaturaId, Integer nota, String comentario) {}
