package com.vereda.controller;

import com.vereda.dto.VagaPublicaDto;
import com.vereda.model.Vaga;
import com.vereda.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vagas/disponiveis")
@CrossOrigin(origins = "*")
public class VagaDisponivelController {

    @Autowired
    private VagaRepository vagaRepository;

    @GetMapping
    public List<VagaPublicaDto> listarVagasAtivas() {
        return vagaRepository.findByAtivaTrue()
                .stream()
                .map(VagaPublicaDto::new)
                .toList();
    }
}
