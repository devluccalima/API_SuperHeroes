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

    //Retornar todos os personagens
    @GetMapping("/personagens")
    public List<Heroi> listarTodos() {
        return repository.findAll();
    }

    //Adicionar entidade
    @PostMapping("/personagens")
    public ResponseEntity<Heroi> adicionarPersonagem(@RequestBody Heroi heroi) {
        Heroi salvo = repository.save(heroi);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    //Buscar entidade por ID
    @GetMapping("/{id}")
    public ResponseEntity<Heroi> buscarPorId(@PathVariable Long id) {
        Optional<Heroi> heroi = repository.findById(id);
        return heroi.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    //Buscar entidade por nome
    @GetMapping("/personagens/nome/{nome}")
    public ResponseEntity<List<Heroi>> buscarPorNome(@PathVariable String nome) {
        List<Heroi> personagens = repository.findByNomeContainingIgnoreCase(nome);
        if (personagens.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personagens);
    }

    //Retornar uma entidade pela identidadeSecreta
    @GetMapping("/personagens/identidade/{identidadeSecreta}")
    public ResponseEntity<List<Heroi>> buscarPorIdentidadeSecreta(@PathVariable String identidadeSecreta) {
        List<Heroi> personagens = repository.findByIdentidadeSecretaContainingIgnoreCase(identidadeSecreta);
        if (personagens.isEmpty()) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personagens);
    }

    @GetMapping("/personagens/poderes/{poder}")
    public ResponseEntity<List<Heroi>> buscarPorPoderes(@PathVariable String poder) {
        List<Heroi> entidade = repository.findByPoderesContainingIgnoreCase(poder);
        if (entidade.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(entidade);
    }

    //Adicionar entidades em lista
    @PostMapping("/personagens/novo")
    public ResponseEntity<List<Heroi>> adicionarLista(@Valid @RequestBody List<Heroi> herois) {
        List<Heroi> salvos = repository.saveAll(herois);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvos);
    }

    //Modificar uma entidade
    @PutMapping("personagens/{id}")
    public ResponseEntity<Heroi> novoHeroi(@PathVariable Long id, @RequestBody Heroi superheroDetails) {
        Optional<Heroi> superhero = repository.findById(id);
        if (superhero.isPresent()) {
            Heroi updatedSuperhero = superhero.get();
            updatedSuperhero.setNome(superheroDetails.getNome());
            updatedSuperhero.setPoderes(superheroDetails.getPoderes());
            updatedSuperhero.setImagem(superheroDetails.getImagem());
            return ResponseEntity.ok(repository.save(updatedSuperhero));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Deletar uma entidade
    @DeleteMapping("/personagens/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Entidade não encontrada");
        } else {
            repository.deleteById(id);
            return ResponseEntity.ok("Heroi deletado com sucesso");
        }
    }
}

