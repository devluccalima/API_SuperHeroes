package com.lucca.desafio_alura.repository;

import com.lucca.desafio_alura.model.Heroi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeroiRepository extends JpaRepository<Heroi, Long> {

        List<Heroi> findByNomeContainingIgnoreCase(String nome);
        List<Heroi> findByIdentidadeSecretaContainingIgnoreCase(String identidadeSecreta);
        List<Heroi> findByPoderesContainingIgnoreCase(String poder);
}