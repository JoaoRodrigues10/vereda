package com.vereda.dto;

import com.vereda.model.enums.StatusCandidatura;

public record TrabalhadorStatusDto(
        Long idCandidatura,
        String nomeTrabalhador,
        String emailTrabalhador,
        String tituloVaga,
        String local,
        StatusCandidatura status,
        Integer nota, // pode ser null
        String comentario // pode ser null
) {}
