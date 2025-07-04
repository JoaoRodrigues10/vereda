package com.vereda.controller;

import com.vereda.controller.Trabalhador.CandidaturaDetalhadaDto;
import com.vereda.dto.TrabalhadorStatusDto;
import com.vereda.model.Candidatura;
import com.vereda.model.enums.StatusCandidatura;
import com.vereda.service.CandidaturaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidaturas")
@CrossOrigin(origins = "*")
public class CandidaturaController {

    @Autowired
    private CandidaturaService candidaturaService;

    @PostMapping
    public Candidatura criar(@RequestBody CandidaturaDto dto) {
        return candidaturaService.criarCandidatura(dto);
    }

    @GetMapping
    public List<Candidatura> listar() {
        return candidaturaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Candidatura buscar(@PathVariable Long id) {
        return candidaturaService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Candidatura não encontrada"));
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        candidaturaService.excluir(id);
    }

    @PutMapping("/{id}/status")
    public Candidatura atualizarStatus(@PathVariable Long id, @RequestParam StatusCandidatura status) {
        return candidaturaService.atualizarStatus(id, status);
    }

    @GetMapping("/vaga/{idVaga}")
    public List<Candidatura> listarPorVaga(@PathVariable Long idVaga) {
        return candidaturaService.buscarPorVaga(idVaga);
    }

    @GetMapping("/aprovadas/{empresaId}")
    public List<CandidaturaDetalhadaDto> listarAprovadasPorEmpresa(@PathVariable Long empresaId) {
        return candidaturaService.listarAprovadasPorEmpresa(empresaId);
    }


    @PutMapping("/{id}/finalizar")
    public Candidatura finalizarContrato(@PathVariable Long id) {
        return candidaturaService.atualizarStatus(id, StatusCandidatura.CONCLUIDA);
    }

    @GetMapping("/finalizadas/{empresaId}")
    public List<CandidaturaDetalhadaDto> listarFinalizadasPorEmpresa(@PathVariable Long empresaId) {
        return candidaturaService.listarFinalizadasPorEmpresa(empresaId);
    }

    @GetMapping("/por-ong/{ongId}")
    public ResponseEntity<List<TrabalhadorStatusDto>> listarPorOng(@PathVariable Long ongId) {
        return ResponseEntity.ok(candidaturaService.listarPorOng(ongId));
    }

}
