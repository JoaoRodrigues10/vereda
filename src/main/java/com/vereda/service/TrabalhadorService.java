package com.vereda.service;

import com.vereda.controller.Empresa.CadastroEmpresaDto;
import com.vereda.controller.Trabalhador.CadastroTrabalhadorDto;
import com.vereda.model.Empresa;
import com.vereda.model.Trabalhador;
import com.vereda.repository.OngRepository;
import com.vereda.repository.TrabalhadorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;
import java.util.List;

@Service
public class TrabalhadorService {
    private TrabalhadorRepository trabalhadorRepository;

    public TrabalhadorService(TrabalhadorRepository trabalhadorRepository) {
        this.trabalhadorRepository = trabalhadorRepository;
    }

    @Autowired
    private OngRepository ongRepository;

    @Transactional
    public Long cadastrarTrabalhador(CadastroTrabalhadorDto cadastroTrabalhadorDto) {
        // Validações
        if (cadastroTrabalhadorDto.cpf() == null || cadastroTrabalhadorDto.cpf().isBlank()) {
            throw new IllegalArgumentException("CPF é obrigatório");
        }

        if (cadastroTrabalhadorDto.data_nascimento() == null) {
            throw new IllegalArgumentException("Data de nascimento é obrigatória");
        }

        var ong = ongRepository.findById(cadastroTrabalhadorDto.idOng())
                .orElseThrow(() -> new IllegalArgumentException("ONG não encontrada"));

        var trabalhador = new Trabalhador(
                null,
                cadastroTrabalhadorDto.nome(),
                cadastroTrabalhadorDto.email(),
                cadastroTrabalhadorDto.cpf(),
                cadastroTrabalhadorDto.data_nascimento(), // Garantido não ser null
                cadastroTrabalhadorDto.habilidades(),
                cadastroTrabalhadorDto.telefone(),
                cadastroTrabalhadorDto.endereco(),
                Instant.now(),
                null
        );
        trabalhador.setOng(ong);

        try {
            return trabalhadorRepository.save(trabalhador).getIdTrabalhador();
        } catch (Exception e) {
            throw new RuntimeException("Falha ao cadastrar trabalhador: " + e.getMessage(), e);
        }
    }

    public long countTrabalhadores() {
        return trabalhadorRepository.count();
    }

    public List<Trabalhador> listarTrabalhadores() {
        return trabalhadorRepository.findAll();
    }


    public List<Trabalhador> listarPorOngId(Long idOng) {
        return trabalhadorRepository.findByOng_IdOng(idOng);
    }


    public long countTrabalhadoresPorOng(Long idOng) {
        return trabalhadorRepository.countByOng_IdOng(idOng);
    }

    public Trabalhador atualizarTrabalhador(Long id, Trabalhador novo) {
        Trabalhador existente = trabalhadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabalhador não encontrado"));

        existente.setNome(novo.getNome());
        existente.setEmail(novo.getEmail());
        existente.setHabilidades(novo.getHabilidades());
        existente.setEndereco(novo.getEndereco());
        existente.setDataNascimento(novo.getDataNascimento());

        // Atualiza a data de atualização para o momento atual
        existente.setUpdateTimestamp(Instant.now());

        return trabalhadorRepository.save(existente);
    }

    public Trabalhador buscarPorId(Long id) {
        return trabalhadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabalhador não encontrado"));
    }

}
