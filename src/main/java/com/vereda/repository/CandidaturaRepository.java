package com.vereda.repository;

import com.vereda.model.Candidatura;
import com.vereda.model.enums.StatusCandidatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
    List<Candidatura> findByVaga_IdVaga(Long idVaga);


    List<Candidatura> findByVaga_Empresa_IdEmpresaAndStatus(Long idEmpresa, StatusCandidatura status);

    List<Candidatura> findByTrabalhador_Ong_IdOng(Long idOng);
}
