package com.vereda.dto;

import com.vereda.model.enums.StatusCandidatura;

public record TrabalhadorStatusDto(
        Long idCandidatura,
        String nomeTrabalhador,
        String emailTrabalhador,
        String telefoneTrabalhador, // <-- Adicionado aqui
        String tituloVaga,
        String local,
        StatusCandidatura status,
        Integer nota,
        String comentario
) {}


