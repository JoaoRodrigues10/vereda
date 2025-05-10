package com.vereda.controller.Trabalhador;

import com.vereda.controller.Ong.CadastroOngDto;
import com.vereda.service.EmpresaService;
import com.vereda.service.OngService;
import com.vereda.service.TrabalhadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ong/trabalhador")
public class TrabalhadorController {
    private final TrabalhadorService trabalhadorService;

    public TrabalhadorController(TrabalhadorService trabalhadorService) {
        this.trabalhadorService = trabalhadorService;
    }

    @PostMapping("/trabalhador")
    public ResponseEntity<Void> cadastroTrabalhador(@RequestBody CadastroTrabalhadorDto cadastroTrabalhadorDto) {
        System.out.println("Dados recebidos: " + cadastroTrabalhadorDto);

        // Validações básicas
        if (cadastroTrabalhadorDto.cpf() == null || cadastroTrabalhadorDto.cpf().isBlank()) {
            System.out.println("CPF não informado");
            return ResponseEntity.badRequest().build();
        }

        if (cadastroTrabalhadorDto.data_nascimento() == null) {
            System.out.println("Data de nascimento não informada");
            return ResponseEntity.badRequest().build();
        }

        try {
            Long trabalhadorId = trabalhadorService.cadastrarTrabalhador(cadastroTrabalhadorDto);
            System.out.println("Trabalhador cadastrado com ID: " + trabalhadorId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar trabalhador: " + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
