package com.vereda.controller.Ong;

import com.vereda.model.Ong;
import com.vereda.repository.OngRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ongs")
public class OngPublicController {

    private final OngRepository ongRepository;

    public OngPublicController(OngRepository ongRepository) {
        this.ongRepository = ongRepository;
    }

    @GetMapping
    public List<Ong> listarOngs() {
        return ongRepository.findAll();
    }
}
