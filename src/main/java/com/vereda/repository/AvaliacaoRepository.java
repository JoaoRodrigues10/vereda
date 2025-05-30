package com.vereda.repository;

import com.vereda.model.Avaliacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
 Optional<Avaliacao> findByCandidatura_IdCandidatura(Long idCandidatura);


}
