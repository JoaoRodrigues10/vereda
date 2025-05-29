package com.vereda.controller.Empresa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmpresaPageController {

    @GetMapping("/empresas")
    public String exibirPaginaEmpresas() {
        return "empresas";
    }
}
