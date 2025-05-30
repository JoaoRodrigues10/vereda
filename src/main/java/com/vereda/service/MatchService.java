package com.vereda.service;

import com.vereda.model.Trabalhador;
import com.vereda.model.Vaga;
import com.vereda.repository.TrabalhadorRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final TrabalhadorRepository trabalhadorRepository;

    public MatchService(TrabalhadorRepository trabalhadorRepository) {
        this.trabalhadorRepository = trabalhadorRepository;
    }

    public List<Trabalhador> encontrarMatchesParaVaga(Vaga vaga) {
        List<Trabalhador> todosTrabalhadores = trabalhadorRepository.findAll();

        Set<String> habilidadesDesejadas = extrairHabilidades(vaga.getDescricao());

        return todosTrabalhadores.stream()
                .filter(t -> vaga.getLocal().equalsIgnoreCase(t.getEndereco())) // ou cidade, conforme o campo
                .filter(t -> {
                    Set<String> habilidadesTrabalhador = extrairHabilidades(t.getHabilidades());
                    return !Collections.disjoint(habilidadesTrabalhador, habilidadesDesejadas);
                })
                .collect(Collectors.toList());
    }

    public List<Vaga> encontrarMatchesParaTrabalhador(Trabalhador trabalhador, List<Vaga> todasVagas) {
        Set<String> habilidadesTrabalhador = extrairHabilidades(trabalhador.getHabilidades());
        System.out.println("Trabalhador habilidades: " + habilidadesTrabalhador);
        System.out.println("Trabalhador endereço: " + trabalhador.getEndereco());
        System.out.println("Comparando com " + todasVagas.size() + " vagas...");

        System.out.println("Trabalhador habilidades: " + habilidadesTrabalhador);
        System.out.println("Total de vagas: " + todasVagas.size());

        return todasVagas.stream()
                .peek(v -> {
                    System.out.println("\nVaga: " + v.getTitulo() + " - Local: " + v.getLocal());
                    System.out.println("Descrição: " + v.getDescricao());
                    System.out.println("Habilidades extraídas da vaga: " + extrairHabilidades(v.getDescricao()));
                })
                .filter(v -> v.getLocal().equalsIgnoreCase(trabalhador.getEndereco()))
                .filter(v -> {
                    Set<String> habilidadesVaga = extrairHabilidades(v.getDescricao());
                    boolean match = !Collections.disjoint(habilidadesTrabalhador, habilidadesVaga);
                    System.out.println("Match de habilidade com trabalhador? " + match);
                    return match;
                })
                .collect(Collectors.toList());

    }

    public Set<String> extrairHabilidades(String texto) {
        if (texto == null || texto.isBlank()) return Set.of();

        return Arrays.stream(texto.toLowerCase()
                        .replaceAll("[:\\-]", " ") // remove dois-pontos e hífens
                        .split("[,;\\.\\n\\r\\s]+")) // quebra por vírgula, ponto, espaço, quebra de linha
                .map(String::trim)
                .filter(s -> !s.isBlank())
                .collect(Collectors.toSet());
    }



}

