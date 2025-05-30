package com.vereda.service;

import com.vereda.model.Avaliacao;
import com.vereda.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao salvar(Avaliacao avaliacao) {
        return avaliacaoRepository.save(avaliacao);
    }

    public Optional<Avaliacao> listarPorCandidatura(Long candidaturaId) {
        return avaliacaoRepository.findByCandidatura_IdCandidatura(candidaturaId);
    }

}
