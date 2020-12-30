package com.example.myapplication.model;

import com.google.firebase.database.Exclude;

public class User {

    private String id;
    private String email;
    private String nome;
    private String senha;

    // Construtor requerido pelo Real Time DataBase
    public User() {

    }

    public User(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
    @Exclude
    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }
}
