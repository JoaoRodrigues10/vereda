package com.vereda.service;

import com.vereda.dto.AuthResponse;
import com.vereda.dto.LoginRequest;
import com.vereda.model.Empresa;
import com.vereda.model.Ong;
import com.vereda.repository.EmpresaRepository;
import com.vereda.repository.OngRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private OngRepository ongRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HttpSession session;

    public AuthResponse autenticarEmpresa(LoginRequest request) {
        Optional<Empresa> empresaOpt = empresaRepository.findByEmail(request.email());
        if (empresaOpt.isEmpty()) {
            return new AuthResponse("Email não encontrado", null);
        }
        Empresa empresa = empresaOpt.get();
        if (!passwordEncoder.matches(request.senha(), empresa.getSenha())) {
            return new AuthResponse("Senha incorreta", null);
        }

        session.setAttribute("empresaId", empresa.getIdEmpresa());
        System.out.println("🔐 empresaId SALVO NA SESSÃO: " + session.getAttribute("empresaId"));
        return new AuthResponse("Login bem-sucedido", "EMPRESA");
    }

    public AuthResponse autenticarOng(LoginRequest request) {
        Optional<Ong> ongOpt = ongRepository.findByEmail(request.email());
        if (ongOpt.isEmpty()) {
            return new AuthResponse("Email não encontrado", null);
        }

        Ong ong = ongOpt.get();
        if (!passwordEncoder.matches(request.senha(), ong.getSenha())) {
            return new AuthResponse("Senha incorreta", null);
        }

        session.setAttribute("ongId", ong.getIdOng());
        System.out.println("🔐 ongId SALVO NA SESSÃO: " + session.getAttribute("ongId"));
        return new AuthResponse("Login bem-sucedido", "ONG");
    }
}
