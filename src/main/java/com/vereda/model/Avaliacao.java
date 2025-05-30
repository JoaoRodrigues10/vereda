package com.vereda.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_candidatura", nullable = false)
    private Candidatura candidatura;

    @Column(nullable = false)
    private Integer nota; // Ex: 1 a 5

    @Column(length = 1000)
    private String comentario;

    private LocalDate dataAvaliacao = LocalDate.now();

    public Avaliacao() {
    }

    public Avaliacao(Long id, Candidatura candidatura, Integer nota, String comentario, LocalDate dataAvaliacao) {
        this.id = id;
        this.candidatura = candidatura;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Candidatura getCandidatura() {
        return candidatura;
    }

    public void setCandidatura(Candidatura candidatura) {
        this.candidatura = candidatura;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDate getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(LocalDate dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }
}

