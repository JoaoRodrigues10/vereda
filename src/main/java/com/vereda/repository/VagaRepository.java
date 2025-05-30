package com.vereda.repository;

import com.vereda.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    List<Vaga> findByAtivaTrue();

    List<Vaga> findByOng_IdOng(Long idOng);



}