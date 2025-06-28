package com.lucca.desafio_alura.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;

@Entity
@Table(name="personagens")
public class Heroi {

    @Id
    @NotNull(message = "O Valor do ID não pode ser nulo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco")
    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    @Column(nullable = false)
    private String nome;


    @NotBlank(message = "O poder não pode estar em branco")
    @Column(nullable = false)
    private String poderes;

    private String imagem;

    @Size(min = 2, max = 50, message = "O nome deve ter entre 2 e 50 caracteres")
    @NotBlank(message = "A identidade-secreta não pode estar em branco")
    @Column(nullable = false)
    private String identidadeSecreta;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPoderes() {
        return poderes;
    }

    public void setPoderes(String poderes) {
        this.poderes = poderes;
    }


    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getIdentidadeSecreta() {
        return identidadeSecreta;
    }

    public void setIdentidadeSecreta(String identidadeSecreta) {
        this.identidadeSecreta = identidadeSecreta;
    }

}

