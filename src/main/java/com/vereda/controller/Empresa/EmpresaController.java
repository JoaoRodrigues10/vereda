package com.vereda.controller.Empresa;

import com.vereda.model.Empresa;
import com.vereda.repository.EmpresaRepository;
import com.vereda.service.EmpresaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/registro")
public class EmpresaController {
    private final EmpresaService empresaService;

    @Autowired
    private EmpresaRepository empresaRepository;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping("/empresa")
    public ResponseEntity<Void> cadastroEmpresa(@RequestBody CadastroEmpresaDto cadastroEmpresaDto) {
        System.out.println("Dados recebidos: " + cadastroEmpresaDto); // Log de debug

        if (cadastroEmpresaDto.cnpj() == null || cadastroEmpresaDto.cnpj().isBlank()) {
            System.out.println("CNPJ não informado"); // Log de debug
            return ResponseEntity.badRequest().build();
        }

        try {
            Long empresaId = empresaService.cadastrarEmpresa(cadastroEmpresaDto);
            System.out.println("Empresa cadastrada com ID: " + empresaId); // Log de debug
            return ResponseEntity.created(URI.create("/api/empresas/" + empresaId)).build();
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar empresa: " + e.getMessage()); // Log de erro
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/perfil")
    public Empresa getPerfil(HttpSession session) {
        Object empresaId = session.getAttribute("empresaId");
        System.out.println("SESSION empresaId: " + empresaId); // 🧪 Debug

        if (empresaId == null) {
            throw new RuntimeException("Empresa não autenticada");
        }

        return empresaRepository.findById((Long) empresaId)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

}