package com.vereda.service;

import com.vereda.model.Trabalhador;
import com.vereda.model.Vaga;
import com.vereda.repository.TrabalhadorRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

        return todasVagas.stream()
                .filter(v -> v.getLocal().equalsIgnoreCase(trabalhador.getEndereco()))
                .filter(v -> {
                    Set<String> habilidadesVaga = extrairHabilidades(v.getDescricao());
                    return !Collections.disjoint(habilidadesTrabalhador, habilidadesVaga);
                })

                .collect(Collectors.toList());


    }

    public Set<String> extrairHabilidades(String descricao) {
        Set<String> habilidadesEncontradas = new HashSet<>();
        List<String> habilidadesConhecidas = List.of("pintura", "soldagem", "carpintaria", "limpeza", "cozinha", "reposição"); // todas em minúsculo

        String descricaoMinuscula = descricao.toLowerCase();

        for (String habilidade : habilidadesConhecidas) {
            if (descricaoMinuscula.contains(habilidade)) {
                habilidadesEncontradas.add(habilidade);
            }
        }

        return habilidadesEncontradas;
    }

}

