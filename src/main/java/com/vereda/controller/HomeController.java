package com.vereda.controller;

import com.vereda.model.Ong;
import com.vereda.repository.OngRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import com.vereda.model.Empresa;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.vereda.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private OngRepository ongRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


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

    @GetMapping("/dashboardEmpresa")
    public String mostrarDashboardEmpresa(Model model, HttpSession session) {
        Long empresaId = (Long) session.getAttribute("empresaId");

        Optional<Empresa> empresaOpt = empresaRepository.findById(empresaId);
        if (empresaOpt.isEmpty()) {
            return "error";
        }

        model.addAttribute("empresa", empresaOpt.get());
        return "dashboardEmpresa";
    }
    @GetMapping("/perfilEmpresa")
    public String mostrarperfilEmpresa(Model model, HttpSession session) {
        Long empresaId = (Long) session.getAttribute("empresaId");

        Optional<Empresa> empresaOpt = empresaRepository.findById(empresaId);
        if (empresaOpt.isEmpty()) {
            return "error";
        }

        model.addAttribute("empresa", empresaOpt.get());
        return "perfilEmpresa";
    }


    @GetMapping("/homeOng")
    public String mostrarHomeOng(Model model, HttpSession session) {
        Long ongId = (Long) session.getAttribute("ongId");

        Optional<Ong> ongOpt = ongRepository.findById(ongId);
        if (ongOpt.isEmpty()) {
            return "error";
        }

        model.addAttribute("ong", ongOpt.get());
        return "homeOng";
    }





    @PostMapping("/empresa/atualizar-perfil")
    public String atualizarPerfil(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String cnpj,
            @RequestParam String setor,
            @RequestParam String endereco,
            @RequestParam String telefone,
            @RequestParam(required = false) String senhaAtual,
            @RequestParam(required =false) String novaSenha,
            @RequestParam(required =false) String confirmarSenha,
            HttpSession session
    ) {
        Long empresaId = (Long) session.getAttribute("empresaId");
        if (empresaId == null) {
            return "redirect:/login-empresa";
        }

        Optional<Empresa> empresaOpt = empresaRepository.findById(empresaId);
        if (empresaOpt.isEmpty()) {
            return "redirect:/empresa/perfil";
        }

        Empresa empresa = empresaOpt.get();
        empresa.setNome(nome);
        empresa.setEmail(email);
        empresa.setCnpj(cnpj);
        empresa.setSetor(setor);
        empresa.setEndereco(endereco);
        empresa.setTelefone(telefone);

        // Atualização de Senha
        if (senhaAtual != null && !senhaAtual.isEmpty()) {
            if (!passwordEncoder.matches(senhaAtual, empresa.getSenha())) {
                // Trate o erro (ex: redirecione com mensagem)
                return "redirect:/empresa/perfil?senhaAtual=invalida";
            }

            if (novaSenha != null && novaSenha.equals(confirmarSenha)) {
                empresa.setSenha(passwordEncoder.encode(novaSenha));
            } else {
                return "redirect:/empresa/perfil?senhas=naoCoincidem";
            }
        }

        empresaRepository.save(empresa);
        return "redirect:/homeEmpresa";
    }

    @GetMapping("/vagasGerais")
    public String mostrarVagasGerais() {
        return "vagasGerais";
    }

}

