package com.vereda.controller.Empresa;

import com.vereda.model.Empresa;
import com.vereda.repository.EmpresaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaPublicController {

    private final EmpresaRepository empresaRepository;

    public EmpresaPublicController(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @GetMapping
    public List<Empresa> listarEmpresas() {
        return empresaRepository.findAll();
    }
}
