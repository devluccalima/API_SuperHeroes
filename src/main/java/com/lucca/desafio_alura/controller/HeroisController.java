package com.lucca.desafio_alura.controller;


import com.lucca.desafio_alura.model.Heroi;
import com.lucca.desafio_alura.repository.HeroiRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/api")
public class HeroisController {

    @Autowired
    private HeroiRepository repository;

    @GetMapping("/personagens")
    public List<Heroi> listarTodos() {
        return repository.findAll();
    }

    @PostMapping("/personagens")
    public ResponseEntity<Heroi> adicionarPersonagem(@RequestBody Heroi heroi) {
        Heroi salvo = repository.save(heroi);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PostMapping("/lote")
    public ResponseEntity<List<Heroi>> adicionarLista(@Valid @RequestBody List<Heroi> herois) {
        List<Heroi> salvos = repository.saveAll(herois);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Heroi> buscarPorId(@PathVariable Long id) {
        Optional<Heroi> heroi = repository.findById(id);
        return heroi.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Buscar personagem por nome
    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<Heroi>> buscarPorNome(@PathVariable String nome) {
        List<Heroi> personagens = repository.findByNomeContainingIgnoreCase(nome);
        if (personagens.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personagens);
    }

    //Inserir Personagen por ID
    @PutMapping("/{id}")
    public ResponseEntity<Heroi> novoHeroi(@PathVariable Long id, @RequestBody Heroi superheroDetails) {
        Optional<Heroi> superhero = repository.findById(id);
        if (superhero.isPresent()) {
            Heroi updatedSuperhero = superhero.get();
            updatedSuperhero.setNome(superheroDetails.getNome());
            updatedSuperhero.setPoderes(superheroDetails.getPoderes());
            return ResponseEntity.ok(repository.save(updatedSuperhero));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Correção para alterar objeto
    @PutMapping
    public ResponseEntity<?> alterarPersonagem(@RequestBody Heroi superheroi) {
        if (superheroi.getId() != null && repository.existsById(superheroi.getId())) {
            Heroi atualizado = repository.save(superheroi);
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não encontrado");
    }


    @DeleteMapping
    public ResponseEntity<?> deletar(@RequestBody Heroi superheroi) {
        if (superheroi.getId() != null && repository.existsById(superheroi.getId())) {
            repository.delete(superheroi);
            return ResponseEntity.ok("Removido com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Personagem não encontrado");
    }

}

