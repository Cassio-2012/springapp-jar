package com.connection.databaseconnection.dto;

import com.connection.databaseconnection.conhecimento.types.TipoConhecimento;

public class InteresseUsuarioDTO {


    private String descricao_interesse, conhecimento, descricao, imagem;
    private Long id;
    private TipoConhecimento tipoConhecimento;

    public InteresseUsuarioDTO(String descricao_interesse, String conhecimento, String descricao, String imagem, Long id, TipoConhecimento tipoConhecimento) {
        this.descricao_interesse = descricao_interesse;
        this.conhecimento = conhecimento;
        this.descricao = descricao;
        this.imagem = imagem;
        this.id = id;
        this.tipoConhecimento = tipoConhecimento;
    }

    public String getDescricao_interesse() {
        return descricao_interesse;
    }

    public void setDescricao_interesse(String descricao_interesse) {
        this.descricao_interesse = descricao_interesse;
    }

    public String getConhecimento() {
        return conhecimento;
    }

    public void setConhecimento(String conhecimento) {
        this.conhecimento = conhecimento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public TipoConhecimento getTipoConhecimento() {
        return tipoConhecimento;
    }

    public void setTipoConhecimento(TipoConhecimento tipoConhecimento) {
        this.tipoConhecimento = tipoConhecimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
