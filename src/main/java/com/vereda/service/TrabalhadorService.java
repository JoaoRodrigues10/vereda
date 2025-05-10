package com.vereda.service;

import com.vereda.controller.Empresa.CadastroEmpresaDto;
import com.vereda.controller.Trabalhador.CadastroTrabalhadorDto;
import com.vereda.model.Empresa;
import com.vereda.model.Trabalhador;
import com.vereda.repository.TrabalhadorRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TrabalhadorService {
    private TrabalhadorRepository trabalhadorRepository;

    public TrabalhadorService(TrabalhadorRepository trabalhador) {
        this.trabalhadorRepository = trabalhadorRepository;
    }

    public Long cadastrarTrabalhador(CadastroTrabalhadorDto cadastroTrabalhadorDto) {
        System.out.println("Iniciando cadastro do colaborador..."); // Log de debug

        if (cadastroTrabalhadorDto.cpf() == null || cadastroTrabalhadorDto.cpf().isBlank()) {
            throw new IllegalArgumentException("CNPJ é obrigatório");
        }

        var trabalhador = new Trabalhador(
                null,
                cadastroTrabalhadorDto.nome(),
                cadastroTrabalhadorDto.email(),
                cadastroTrabalhadorDto.cpf(),
                cadastroTrabalhadorDto.data_nascimento(),
                cadastroTrabalhadorDto.telefone(),
                cadastroTrabalhadorDto.endereco(),
                Instant.now(),
                null
        );

        System.out.println("Colaborador a ser salvo: " + trabalhador); // Log de debug

        try {
            Trabalhador saved = trabalhadorRepository.save(trabalhador);
            System.out.println("Colaborador salvo com ID: " + saved.getIdTrabalhador()); // Log de debug
            return saved.getIdTrabalhador();
        } catch (Exception e) {
            System.out.println("Erro ao salvar : " + e.getMessage()); // Log de erro
            throw e;
        }
    }
}
