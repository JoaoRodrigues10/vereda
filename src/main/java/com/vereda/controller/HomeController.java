package com.vereda.controller;

import org.springframework.ui.Model;
import com.vereda.model.Empresa;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.vereda.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private EmpresaRepository empresaRepository;


    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/registroEmpresa")
    public String mostrarFormularioEmpresa() {
        return "registroEmpresa";

    }

    @GetMapping("/registroOng")
    public String mostrarFormularioOng() {
        return "registroOng";
    }

    @GetMapping("/login-empresa")
    public String mostrarLoginEmpresa() {
        return "login-empresa";
    }

    @GetMapping("/login-ong")
    public String mostrarLoginOng() {
        return "login-ong";
    }

    @GetMapping("/homeEmpresa")
    public String mostrarHomeEmpresa(Model model, HttpSession session) {
        Long empresaId = (Long) session.getAttribute("empresaId");

        Optional<Empresa> empresaOpt = empresaRepository.findById(empresaId);
        if (empresaOpt.isEmpty()) {
            return "error";
        }

        model.addAttribute("empresa", empresaOpt.get());
        return "homeEmpresa";
    }

    @GetMapping("/homeOng")
    public String mostrarHomeOng() {
        return "homeOng";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // encerra a sessão atual
        return "home";
    }



}

