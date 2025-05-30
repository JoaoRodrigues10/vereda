package com.vereda.repository;

import com.vereda.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByCandidatura_IdCandidatura(Long idCandidatura);

}
