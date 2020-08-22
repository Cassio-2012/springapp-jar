package com.connection.databaseconnection.dto;

import lombok.Data;

public class BuscaDTO {

    private long id, nivel;
    private String nome , email, imagem, conhecimento, local, titulo;


    public BuscaDTO(long id, long nivel, String nome, String email, String imagem, String conhecimento,
                    String local, String titulo) {
        this.id = id;
        this.nivel = nivel;
        this.nome = nome;
        this.email = email;
        this.imagem = imagem;
        this.conhecimento = conhecimento;
        this.local = local;
        this.titulo = titulo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNivel() {
        return nivel;
    }

    public void setNivel(long nivel) {
        this.nivel = nivel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getConhecimento() {
        return conhecimento;
    }

    public void setConhecimento(String conhecimento) {
        this.conhecimento = conhecimento;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
