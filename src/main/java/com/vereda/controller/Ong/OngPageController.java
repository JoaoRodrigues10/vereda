package com.vereda.controller.Ong;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OngPageController {

    @GetMapping("/ongs")
    public String exibirPaginaOngs() {
        return "ongs";
    }
}
