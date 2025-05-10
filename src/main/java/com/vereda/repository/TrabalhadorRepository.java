package com.vereda.repository;

import com.vereda.model.Empresa;
import com.vereda.model.Trabalhador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrabalhadorRepository extends JpaRepository<Trabalhador, Long> {
    Optional<Trabalhador> findByEmail(String email);
}

