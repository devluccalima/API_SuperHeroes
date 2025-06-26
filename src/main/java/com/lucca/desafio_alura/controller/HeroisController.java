package com.lucca.desafio_alura.controller;

import com.lucca.desafio_alura.model.Heroi;
import com.lucca.desafio_alura.repository.HeroiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;





@RestController
@RequestMapping("/superherois")
public class HeroisController {


    @Autowired
    private HeroiRepository repository;

    @GetMapping
    public List<Heroi> listar() {
        return repository.findAll();
    }

    @PostMapping
    public List<Heroi> adicionarLista(@RequestBody List<Heroi> superherois) {
        return repository.saveAll(superherois);
    }

    //Correção para alterar objeto
    @PutMapping
    public ResponseEntity<?> alterar(@RequestBody Heroi superheroi) {
        if (superheroi.getId() != null && repository.existsById(superheroi.getId())) {
            Heroi atualizado = repository.save(superheroi);
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("SuperHeroi não encontrado");
    }

    @DeleteMapping
    public ResponseEntity<?> deletar(@RequestBody Heroi superheroi) {
        if (superheroi.getId() != null && repository.existsById(superheroi.getId())) {
            repository.delete(superheroi);
            return ResponseEntity.ok("Removido com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Superherói não encontrado");
    }

}

