package com.vereda.service;

import com.vereda.controller.Empresa.CadastroEmpresaDto;
import com.vereda.controller.Empresa.EmpresaController;
import com.vereda.model.Empresa;
import com.vereda.repository.EmpresaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;
    private final PasswordEncoder passwordEncoder;

    public EmpresaService(EmpresaRepository empresaRepository, PasswordEncoder passwordEncoder) {
        this.empresaRepository = empresaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long cadastrarEmpresa(CadastroEmpresaDto cadastroEmpresaDto) {
        System.out.println("Iniciando cadastro de empresa..."); // Log de debug

        if (cadastroEmpresaDto.cnpj() == null || cadastroEmpresaDto.cnpj().isBlank()) {
            throw new IllegalArgumentException("CNPJ é obrigatório");
        }

        var empresa = new Empresa(
                null,
                cadastroEmpresaDto.nome(),
                cadastroEmpresaDto.email(),
                passwordEncoder.encode(cadastroEmpresaDto.senha()),
                cadastroEmpresaDto.telefone(),
                cadastroEmpresaDto.endereco(),
                cadastroEmpresaDto.setor(),
                cadastroEmpresaDto.cnpj(),
                Instant.now(),
                null
        );

        System.out.println("Empresa a ser salva: " + empresa); // Log de debug

        try {
            Empresa saved = empresaRepository.save(empresa);
            System.out.println("Empresa salva com ID: " + saved.getIdEmpresa()); // Log de debug
            return saved.getIdEmpresa();
        } catch (Exception e) {
            System.out.println("Erro ao salvar empresa: " + e.getMessage()); // Log de erro
            throw e;
        }
    }

    public Empresa buscarPorEmail(String email) {
        return empresaRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada com email: " + email));
    }

}
