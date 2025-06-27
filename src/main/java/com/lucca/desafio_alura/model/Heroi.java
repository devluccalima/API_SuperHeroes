package com.lucca.desafio_alura.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String poderes;

    private String imagem;

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

