package com.vereda.controller;

import com.vereda.dto.AuthResponse;
import com.vereda.dto.LoginRequest;
import com.vereda.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/empresa")
    public AuthResponse loginEmpresa(@RequestBody LoginRequest request) {
        return loginService.autenticarEmpresa(request);
    }

    @PostMapping("/ong")
    public AuthResponse loginOng(@RequestBody LoginRequest request) {
        return loginService.autenticarOng(request);
    }
}
