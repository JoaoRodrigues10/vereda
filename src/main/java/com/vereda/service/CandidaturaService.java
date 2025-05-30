package com.vereda.service;

import com.vereda.controller.CandidaturaDto;
import com.vereda.controller.Trabalhador.CandidaturaDetalhadaDto;
import com.vereda.dto.AvaliacaoDto;
import com.vereda.dto.TrabalhadorStatusDto;
import com.vereda.model.Avaliacao;
import com.vereda.model.Candidatura;
import com.vereda.model.Trabalhador;
import com.vereda.model.Vaga;
import com.vereda.model.enums.StatusCandidatura;
import com.vereda.repository.AvaliacaoRepository;
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
    @Autowired
    private AvaliacaoRepository avaliacaoRepository;



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



    public Avaliacao criarAvaliacao(AvaliacaoDto dto) {
        Candidatura candidatura = candidaturaRepository.findById(dto.candidaturaId())
                .orElseThrow(() -> new RuntimeException("Candidatura não encontrada"));

        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setCandidatura(candidatura);
        avaliacao.setComentario(dto.comentario());
        avaliacao.setNota(dto.nota());

        return avaliacaoRepository.save(avaliacao);
    }



    public List<CandidaturaDetalhadaDto> listarAprovadasPorEmpresa(Long empresaId) {
        List<Candidatura> candidaturasAprovadas = candidaturaRepository
                .findByVaga_Empresa_IdEmpresaAndStatus(empresaId, StatusCandidatura.ACEITA);

        return candidaturasAprovadas.stream().map(c -> new CandidaturaDetalhadaDto(
                c.getIdCandidatura(),
                c.getTrabalhador().getNome(),
                c.getTrabalhador().getEmail(),
                c.getVaga().getTitulo(),
                c.getVaga().getLocal(),
                c.getStatus()
        )).toList();
    }

    public List<CandidaturaDetalhadaDto> listarFinalizadasPorEmpresa(Long empresaId) {
        List<Candidatura> candidaturasFinalizadas = candidaturaRepository
                .findByVaga_Empresa_IdEmpresaAndStatus(empresaId, StatusCandidatura.CONCLUIDA);

        return candidaturasFinalizadas.stream().map(c -> new CandidaturaDetalhadaDto(
                c.getIdCandidatura(),
                c.getTrabalhador().getNome(),
                c.getTrabalhador().getEmail(),
                c.getVaga().getTitulo(),
                c.getVaga().getLocal(),
                c.getStatus()
        )).toList();
    }

    public List<TrabalhadorStatusDto> listarPorOng(Long ongId) {
        List<Candidatura> candidaturas = candidaturaRepository.findByTrabalhador_Ong_IdOng(ongId);

        return candidaturas.stream().map(c -> {
            Avaliacao avaliacao = avaliacaoRepository
                    .findByCandidatura_IdCandidatura(c.getIdCandidatura())
                    .orElse(null);
            return new TrabalhadorStatusDto(
                    c.getIdCandidatura(),
                    c.getTrabalhador().getNome(),
                    c.getTrabalhador().getEmail(),
                    c.getVaga().getTitulo(),
                    c.getVaga().getLocal(),
                    c.getStatus(),
                    avaliacao != null ? avaliacao.getNota() : null,
                    avaliacao != null ? avaliacao.getComentario() : null
            );
        }).toList();
    }




}