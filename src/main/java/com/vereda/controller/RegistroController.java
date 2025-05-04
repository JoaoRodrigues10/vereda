package com.vereda.controller;

import com.vereda.model.Usuario;
import com.vereda.service.RegistroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registro")
public class RegistroController {

    @Autowired
    private RegistroService registroService;

    @PostMapping("/empresa")
    public ResponseEntity<?> cadastrarEmpresa(@RequestBody Usuario usuario) {
        // Força o papel a ser "EMPRESA" (evita manipulação pelo usuário)
        usuario.setPapel("EMPRESA");
        return registroService.registrarEmpresa(usuario);
    }
}