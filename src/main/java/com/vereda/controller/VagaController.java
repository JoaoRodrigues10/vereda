package com.vereda.controller;

import com.vereda.dto.VagaDto;
import com.vereda.model.Vaga;
import com.vereda.service.VagaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas")
public class VagaController {

    private final VagaService vagaService;

    public VagaController(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    @PostMapping
    public ResponseEntity<Vaga> criarVaga(@RequestBody @Valid VagaDto dto) {
        Vaga vaga = vagaService.criarVaga(dto);
        return ResponseEntity.ok(vaga);
    }

    @GetMapping
    public ResponseEntity<List<Vaga>> listarVagas() {
        return ResponseEntity.ok(vagaService.listarVagas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaga> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(vagaService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        vagaService.deletarVaga(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/desativar")
    public ResponseEntity<Vaga> desativar(@PathVariable Long id) {
        return ResponseEntity.ok(vagaService.desativarVaga(id));
    }

    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<List<Vaga>> listarVagasPorEmpresa(@PathVariable Long empresaId) {
        return ResponseEntity.ok(vagaService.listarVagasPorEmpresa(empresaId));
    }


}
