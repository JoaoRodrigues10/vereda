package com.vereda.service;

import com.vereda.controller.Ong.CadastroOngDto;
import com.vereda.model.Ong;
import com.vereda.repository.EmpresaRepository;
import com.vereda.repository.OngRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class OngService {
    private final OngRepository ongRepository;
    private  final PasswordEncoder passwordEncoder;

    public OngService(OngRepository ongRepository, PasswordEncoder passwordEncoder, EmpresaRepository empresaRepository) {
        this.ongRepository = ongRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Long cadastrarOng(CadastroOngDto cadastroOngDto) {
        System.out.println(cadastroOngDto);

        if (cadastroOngDto.cnpj()== null || cadastroOngDto.cnpj().isBlank()){
            throw new IllegalArgumentException("CNPJ é obrigatório");
        }

        var ong = new Ong(
                null,
                cadastroOngDto.nome(),
                cadastroOngDto.email(),
                passwordEncoder.encode(cadastroOngDto.senha()),
                cadastroOngDto.telefone(),
                cadastroOngDto.endereco(),
                cadastroOngDto.setor(),
                cadastroOngDto.cnpj(),
                Instant.now(),
                null

        );

        System.out.println("Ong a ser salva: " + ong);

        try {
            Ong saved = ongRepository.save(ong);
            System.out.println("Ong salva com ID: " + saved.getIdOng());
            return saved.getIdOng();
        }catch (Exception e){
            System.out.println("Erro ao salvar empresa" + e.getMessage());
            throw e;
        }
    }

}
