package com.connection.databaseconnection.dto;

import com.connection.databaseconnection.posts.Post;
import com.connection.databaseconnection.usuario.Usuario;

import javax.persistence.*;

public class ReacoesDTO {

    private Long id;
    private Integer tipo;
    private long id_user;
    private long id_post;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public long getId_post() {
        return id_post;
    }

    public void setId_post(long id_post) {
        this.id_post = id_post;
    }
}
