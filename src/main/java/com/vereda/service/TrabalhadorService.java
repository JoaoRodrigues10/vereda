package com.vereda.service;

import com.vereda.controller.Empresa.CadastroEmpresaDto;
import com.vereda.controller.Trabalhador.CadastroTrabalhadorDto;
import com.vereda.model.Empresa;
import com.vereda.model.Trabalhador;
import com.vereda.repository.OngRepository;
import com.vereda.repository.TrabalhadorRepository;
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
}
