package com.example.armazenar_contatos.model;

public class Contato {

    private int id;
    private String nome;

    public Contato(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
}
