package com.vereda.controller.Ong;

import com.vereda.model.Ong;
import com.vereda.service.EmpresaService;
import com.vereda.service.OngService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/registro")
public class OngController {
    private final EmpresaService empresaService;
    private OngService ongService;

    public OngController(OngService ongService, EmpresaService empresaService) {
        this.ongService = ongService;
        this.empresaService = empresaService;
    }

    @PostMapping("/ong")
    public ResponseEntity<Void> cadastroOng(@RequestBody CadastroOngDto cadastroOngDto) {
        System.out.println("Dados recebidos: " + cadastroOngDto);

        if(cadastroOngDto.cnpj() == null || cadastroOngDto.cnpj().isBlank()){
            System.out.println("CNPJ não informado");
            return ResponseEntity.badRequest().build();
        }

        try{
            Long ongId = ongService.cadastrarOng(cadastroOngDto);
            System.out.println("Ong cadastrada com ID:" + ongId);
            return ResponseEntity.ok().build();

        }catch (Exception e) {
            System.out.println( "Erro ao cadastrar ong" + e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
