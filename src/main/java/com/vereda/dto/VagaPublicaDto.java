package com.vereda.dto;

import com.vereda.model.Vaga;

public record VagaPublicaDto(
        Long id,
        String titulo,
        String descricao,
        String local,
        boolean ativa,
        String nomeEmpresa
) {
    public VagaPublicaDto(Vaga vaga) {
        this(
                vaga.getIdVaga(),
                vaga.getTitulo(),
                vaga.getDescricao(),
                vaga.getLocal(),
                vaga.isAtiva(),
                vaga.getEmpresa().getNome()
        );
    }
}
