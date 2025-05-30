package com.vereda.service;

import com.vereda.controller.CandidaturaDto;
import com.vereda.model.Candidatura;
import com.vereda.model.Trabalhador;
import com.vereda.model.Vaga;
import com.vereda.model.enums.StatusCandidatura;
import com.vereda.repository.CandidaturaRepository;
import com.vereda.repository.TrabalhadorRepository;
import com.vereda.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CandidaturaService {

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    @Autowired
    private TrabalhadorRepository trabalhadorRepository;

    @Autowired
    private VagaRepository vagaRepository;

    public Candidatura criarCandidatura(CandidaturaDto dto) {
        Trabalhador trabalhador = trabalhadorRepository.findById(dto.trabalhadorId())
                .orElseThrow(() -> new RuntimeException("Trabalhador não encontrado"));

        Vaga vaga = vagaRepository.findById(dto.idVaga())
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada"));

        Candidatura candidatura = new Candidatura();
        candidatura.setTrabalhador(trabalhador);
        candidatura.setVaga(vaga);
        candidatura.setStatus(dto.status() != null ? dto.status() : StatusCandidatura.PENDENTE);

        return candidaturaRepository.save(candidatura);
    }


    public List<Candidatura> listarTodas() {
        return candidaturaRepository.findAll();
    }

    public Optional<Candidatura> buscarPorId(Long id) {
        return candidaturaRepository.findById(id);
    }

    public void excluir(Long id) {
        candidaturaRepository.deleteById(id);
    }

    public Candidatura atualizarStatus(Long id, StatusCandidatura novoStatus) {
        Candidatura candidatura = candidaturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidatura não encontrada"));
        candidatura.setStatus(novoStatus);
        return candidaturaRepository.save(candidatura);
    }

    public List<Candidatura> buscarPorVaga(Long idVaga) {
        return candidaturaRepository.findByVaga_IdVaga(idVaga);
    }

}