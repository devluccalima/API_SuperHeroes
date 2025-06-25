package com.lucca.desafio_alura.repository;

import com.lucca.desafio_alura.model.Heroi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeroiRepository extends JpaRepository<Heroi, Long> {

}