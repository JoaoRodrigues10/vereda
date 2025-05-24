package com.vereda.controller.Trabalhador;

import com.vereda.controller.Ong.CadastroOngDto;
import com.vereda.service.EmpresaService;
import com.vereda.service.OngService;
import com.vereda.service.TrabalhadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/ong/trabalhador")
@CrossOrigin(origins = "*") // Adicione esta anotação para habilitar CORS
public class TrabalhadorController {
    private final TrabalhadorService trabalhadorService;

    public TrabalhadorController(TrabalhadorService trabalhadorService) {
        this.trabalhadorService = trabalhadorService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> cadastroTrabalhador(@Valid @RequestBody CadastroTrabalhadorDto cadastroTrabalhadorDto) {
        try {
            // Validações adicionais
            if (cadastroTrabalhadorDto.cpf() == null || cadastroTrabalhadorDto.cpf().isBlank()) {
                return ResponseEntity.badRequest().body(Map.of(
                        "success", false,
                        "message", "CPF é obrigatório"
                ));
            }

            Long trabalhadorId = trabalhadorService.cadastrarTrabalhador(cadastroTrabalhadorDto);

            return ResponseEntity.ok().body(Map.of(
                    "success", true,
                    "id", trabalhadorId,
                    "message", "Trabalhador cadastrado com sucesso"
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countTrabalhadores() {
        long count = trabalhadorService.countTrabalhadores();
        return ResponseEntity.ok(count);
    }
}
