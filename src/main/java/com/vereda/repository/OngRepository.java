package com.vereda.repository;

import com.vereda.model.Empresa;
import com.vereda.model.Ong;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OngRepository extends JpaRepository<Ong, Long>  {
    Optional<Ong> findByEmail(String email);
}
