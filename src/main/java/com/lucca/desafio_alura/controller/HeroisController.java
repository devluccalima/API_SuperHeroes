package com.lucca.desafio_alura.controller;

import com.lucca.desafio_alura.model.Heroi;
import com.lucca.desafio_alura.repository.HeroiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/superherois")
public class HeroisController {
    @Autowired
    private HeroiRepository heroiRepository;

    // GET: Listar todos
    @GetMapping
    public List<Heroi> listarTodos() {
        return heroiRepository.findAll();
    }

    // GET: Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Heroi> buscarPorId(@PathVariable Long id) {
        return heroiRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Criar novo
    @PostMapping
    public ResponseEntity<Heroi> adicionar(@Valid @RequestBody Heroi heroi) {
        Heroi salvo = heroiRepository.save(heroi);
        return ResponseEntity.status(201).body(salvo);
    }

    // POST: Adicionar lista (se necessário)
    @PostMapping("/lista")
    public List<Heroi> adicionarLista(@Valid @RequestBody List<Heroi> herois) {
        return heroiRepository.saveAll(herois);
    }

    // PUT: Atualizar existente
    @PutMapping("/{id}")
    public ResponseEntity<Heroi> alterar(@PathVariable Long id, @Valid @RequestBody Heroi heroi) {
        if (!heroiRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        heroi.setId(id);
        return ResponseEntity.ok(heroiRepository.save(heroi));
    }

    // DELETE: Excluir por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!heroiRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        heroiRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}