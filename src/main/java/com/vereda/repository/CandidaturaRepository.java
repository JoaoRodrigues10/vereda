package com.vereda.repository;

import com.vereda.model.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
    List<Candidatura> findByVaga_IdVaga(Long idVaga);


}
