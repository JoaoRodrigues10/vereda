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
    public String mostrarFormulario() {
        return "registroEmpresa";
    }
}
