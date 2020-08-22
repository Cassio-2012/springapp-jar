package com.connection.databaseconnection.conhecimento.types;

public class TypeModel {

    private String tipo;
    private String Key;

    public TypeModel(String tipo, String key) {
        this.tipo = tipo;
        Key = key;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
