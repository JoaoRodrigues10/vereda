package com.vereda.controller.Trabalhador;

import com.vereda.controller.Ong.CadastroOngDto;
import com.vereda.model.Trabalhador;
import com.vereda.service.EmpresaService;
import com.vereda.service.OngService;
import com.vereda.service.TrabalhadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("/count/{idOng}")
    public ResponseEntity<Map<String, Long>> countTrabalhadoresPorOng(@PathVariable Long idOng) {
        long count = trabalhadorService.countTrabalhadoresPorOng(idOng);
        return ResponseEntity.ok(Map.of("count", count));
    }


    @GetMapping("/list")
    public ResponseEntity<List<Trabalhador>> listarTrabalhadores() {
        List<Trabalhador> trabalhadores = trabalhadorService.listarTrabalhadores(); // CORRETO - usando o service
        return ResponseEntity.ok(trabalhadores);
    }

    @GetMapping("/porOng/{idOng}")
    public ResponseEntity<List<Trabalhador>> listarPorOng(@PathVariable Long idOng) {
        return ResponseEntity.ok(trabalhadorService.listarPorOngId(idOng));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Trabalhador> atualizarTrabalhador(@PathVariable Long id, @RequestBody Trabalhador trabalhadorAtualizado) {
        Trabalhador atualizado = trabalhadorService.atualizarTrabalhador(id, trabalhadorAtualizado);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Trabalhador> buscarPorId(@PathVariable Long id) {
        Trabalhador trabalhador = trabalhadorService.buscarPorId(id);
        return ResponseEntity.ok(trabalhador);
    }



}
