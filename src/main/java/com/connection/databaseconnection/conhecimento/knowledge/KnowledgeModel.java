package com.connection.databaseconnection.conhecimento.knowledge;

public class KnowledgeModel {

    private long id;
    private String key;

    public KnowledgeModel(long id, String key) {
        this.id = id;
        this.key = key;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
