package com.vereda.controller.Trabalhador;

import java.time.LocalDate;

public record CadastroTrabalhadorDto(String nome, String email, String cpf, LocalDate data_nascimento, String telefone, String endereco) {
}
