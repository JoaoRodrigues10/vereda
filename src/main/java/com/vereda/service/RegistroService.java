package com.vereda.service;

import com.vereda.model.Usuario;
import com.vereda.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@Service
public class RegistroService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<?> registrarEmpresa(Usuario usuario) {
        // Validação manual (caso as anotações não funcionem)
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Nome é obrigatório.");
        }
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Email é obrigatório.");
        }
        if (usuario.getSenha() == null || usuario.getSenha().length() < 6) {
            return ResponseEntity.badRequest().body("Senha deve ter pelo menos 6 caracteres.");
        }

        // Validação de email único
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado.");
        }

        // Codificação de senha
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));

        // Garante que o papel seja "EMPRESA"
        usuario.setPapel("EMPRESA");

        // Salva no banco
        usuarioRepository.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}