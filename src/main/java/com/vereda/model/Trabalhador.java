package com.vereda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tb_trabalhador")
public class Trabalhador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrabalhador;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "cpf", nullable = false)
    private String cpf;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;



    @Column(name = "habilidades")
    private String habilidades;


    @Column(name = "telefone")
    private String telefone;

    @Column(name = "endereco")
    private String endereco;

    @CreationTimestamp
    private Instant creationTimestamp;

    @UpdateTimestamp
    private Instant updateTimestamp;

    @ManyToOne
    @JoinColumn(name = "id_ong", nullable = false)
    private Ong ong;

    public Ong getOng() {
        return ong;
    }

    public void setOng(Ong ong) {
        this.ong = ong;
    }

    public Trabalhador(){

    }

    public Trabalhador(Long idTrabalhador, String nome, String email, String cpf, LocalDate data_nascimento, String habilidades, String telefone, String endereco, Instant creationTimestamp, Instant updateTimestamp) {
        this.idTrabalhador = idTrabalhador;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.dataNascimento = data_nascimento;
        this.habilidades = habilidades;
        this.telefone = telefone;
        this.endereco = endereco;
        this.creationTimestamp = creationTimestamp;
        this.updateTimestamp = updateTimestamp;
    }

    public Long getIdTrabalhador() {
        return idTrabalhador;
    }

    public void setIdTrabalhador(Long idTrabalhador) {
        this.idTrabalhador = idTrabalhador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(String habilidades) {
        this.habilidades = habilidades;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Instant getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(Instant creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public Instant getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(Instant updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }
}
