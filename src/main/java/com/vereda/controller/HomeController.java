package com.vereda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

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
    public String mostrarHomeEmpresa() {
        return "homeEmpresa";
    }

    @GetMapping("/homeOng")
    public String mostrarHomeOng() {
        return "homeOng";
    }

}

