package com.vereda.model;

import com.vereda.model.enums.StatusCandidatura;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "tb_candidatura")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCandidatura;

    @ManyToOne
    @JoinColumn(name = "trabalhador_id", nullable = false)
    private Trabalhador trabalhador;

    @ManyToOne
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusCandidatura status;

    @CreationTimestamp
    private Instant dataCandidatura;


    public Candidatura(){

    }
    public Candidatura(Long idCandidatura, Trabalhador trabalhador, Vaga vaga, StatusCandidatura status, Instant dataCandidatura) {
        this.idCandidatura = idCandidatura;
        this.trabalhador = trabalhador;
        this.vaga = vaga;
        this.status = status;
        this.dataCandidatura = dataCandidatura;
    }

    public Long getIdCandidatura() {
        return idCandidatura;
    }

    public void setIdCandidatura(Long idCandidatura) {
        this.idCandidatura = idCandidatura;
    }

    public Trabalhador getTrabalhador() {
        return trabalhador;
    }

    public void setTrabalhador(Trabalhador trabalhador) {
        this.trabalhador = trabalhador;
    }

    public Vaga getVaga() {
        return vaga;
    }

    public void setVaga(Vaga vaga) {
        this.vaga = vaga;
    }

    public StatusCandidatura getStatus() {
        return status;
    }

    public void setStatus(StatusCandidatura status) {
        this.status = status;
    }

    public Instant getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(Instant dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }
}