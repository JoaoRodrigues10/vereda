package com.vereda.controller;

import com.vereda.dto.AvaliacaoDto;
import com.vereda.model.Avaliacao;
import com.vereda.service.AvaliacaoService;
import com.vereda.service.CandidaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/avaliacoes")
@CrossOrigin(origins = "*")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @Autowired
    private CandidaturaService candidaturaService;

    @GetMapping("/candidatura/{id}")
    public Optional<Avaliacao> listarPorCandidatura(@PathVariable Long id) {
        return avaliacaoService.listarPorCandidatura(id); // CORRIGIDO AQUI
    }

    @PostMapping
    public ResponseEntity<?> avaliar(@RequestBody AvaliacaoDto dto) {
        try {
            Avaliacao nova = candidaturaService.criarAvaliacao(dto);
            return ResponseEntity.ok(nova);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Erro ao salvar avaliação: " + e.getMessage());
        }
    }
}


