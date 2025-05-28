package com.vereda.service;

import com.vereda.dto.VagaDto;
import com.vereda.model.Empresa;
import com.vereda.model.Vaga;
import com.vereda.repository.EmpresaRepository;
import com.vereda.repository.VagaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {

    private final VagaRepository vagaRepository;
    private final EmpresaRepository empresaRepository;

    public VagaService(VagaRepository vagaRepository, EmpresaRepository empresaRepository) {
        this.vagaRepository = vagaRepository;
        this.empresaRepository = empresaRepository;
    }

    public Vaga criarVaga(@Valid VagaDto dto) {
        Empresa empresa = empresaRepository.findById(dto.empresaId())
                .orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));

        Vaga vaga = new Vaga();
        vaga.setTitulo(dto.titulo());
        vaga.setDescricao(dto.descricao());
        vaga.setLocal(dto.local());
        vaga.setAtiva(true);
        vaga.setEmpresa(empresa);

        return vagaRepository.save(vaga);
    }

    public List<Vaga> listarVagas() {
        return vagaRepository.findAll();
    }

    public Vaga buscarPorId(Long id) {
        return vagaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vaga não encontrada"));
    }

    public void deletarVaga(Long id) {
        Vaga vaga = buscarPorId(id);
        vagaRepository.delete(vaga);
    }

    public Vaga desativarVaga(Long id) {
        Vaga vaga = buscarPorId(id);
        vaga.setAtiva(false);
        return vagaRepository.save(vaga);
    }

    public Vaga salvar(Vaga vaga) {
        return vagaRepository.save(vaga);
    }

}
