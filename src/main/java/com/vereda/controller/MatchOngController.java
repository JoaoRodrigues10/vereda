package com.vereda.controller;

import com.vereda.model.Trabalhador;
import com.vereda.model.Vaga;
import com.vereda.repository.TrabalhadorRepository;
import com.vereda.repository.VagaRepository;
import com.vereda.service.MatchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ongs/match") // indica que é para as ONGs
public class MatchOngController {

    private final MatchService matchService;
    private final VagaRepository vagaRepository;
    private final TrabalhadorRepository trabalhadorRepository;

    public MatchOngController(MatchService matchService, VagaRepository vagaRepository, TrabalhadorRepository trabalhadorRepository) {
        this.matchService = matchService;
        this.vagaRepository = vagaRepository;
        this.trabalhadorRepository = trabalhadorRepository;
    }

    @GetMapping("/vaga/{id}")
    public List<Trabalhador> recomendarTrabalhadoresParaVaga(@PathVariable Long id) {
        Vaga vaga = vagaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

        return matchService.encontrarMatchesParaVaga(vaga);
    }
    @GetMapping("/trabalhador/{id}")
    public List<Vaga> recomendarVagasParaTrabalhador(@PathVariable Long id) {
        Trabalhador trabalhador = trabalhadorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trabalhador não encontrado"));

        List<Vaga> todasVagas = vagaRepository.findAll();
        return matchService.encontrarMatchesParaTrabalhador(trabalhador, todasVagas);
    }

}

