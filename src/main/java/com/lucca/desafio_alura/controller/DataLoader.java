package com.lucca.desafio_alura.controller;

import com.lucca.desafio_alura.model.Heroi;
import com.lucca.desafio_alura.repository.HeroiRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.InputStream;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDataBase(HeroiRepository heroiRepository) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Heroi>> typeReference = new TypeReference<>() {};
            InputStream inputStream = getClass().getResourceAsStream("/personagens_simplificados.json");
            try {
                List<Heroi> herois = mapper.readValue(inputStream, typeReference);
                heroiRepository.saveAll(herois);
                System.out.println("Personagens Carregados");
            } catch (Exception e) {
                System.out.println("Erro ao carregar os personagens" + e.getMessage());
            }
        };
    }

}
