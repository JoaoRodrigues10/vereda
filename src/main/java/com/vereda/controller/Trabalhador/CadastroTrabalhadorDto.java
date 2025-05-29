package com.vereda.controller.Trabalhador;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CadastroTrabalhadorDto(
        @NotBlank String nome,
        @NotBlank @Email String email,
        @NotBlank String cpf,
        @NotNull LocalDate data_nascimento,
        String telefone,
        String endereco,
        String habilidades,
        @NotNull Long idOng
) {}
