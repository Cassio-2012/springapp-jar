package com.connection.databaseconnection.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

public class UsuarioViewDTO {

    private String sobre, photo;
    long id;

    public UsuarioViewDTO(String sobre, String photo, long id) {
        this.sobre = sobre;
        this.photo = photo;
        this.id = id;
    }

    public String getSobre() {
        return sobre;
    }

    public void setSobre(String sobre) {
        this.sobre = sobre;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}



