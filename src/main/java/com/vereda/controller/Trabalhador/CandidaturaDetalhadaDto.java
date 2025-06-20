package com.vereda.controller.Trabalhador;


import com.vereda.model.enums.StatusCandidatura;

public record CandidaturaDetalhadaDto(
        Long idCandidatura,
        String nomeTrabalhador,
        String emailTrabalhador,
        String titulo,
        String local,
        StatusCandidatura status
) {}
