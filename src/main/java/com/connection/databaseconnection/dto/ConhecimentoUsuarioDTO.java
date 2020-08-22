package com.connection.databaseconnection.dto;

import com.connection.databaseconnection.conhecimento.types.TipoConhecimento;

public class ConhecimentoUsuarioDTO {

    private String descricao_user, conhecimento, descricao, imagem;
    private Integer nivel;
    private Long id;
    private TipoConhecimento tipoConhecimento;

    public ConhecimentoUsuarioDTO(String descricao_user, String conhecimento, String descricao, String imagem, Integer nivel, Long id, TipoConhecimento tipoConhecimento) {
        this.descricao_user = descricao_user;
        this.conhecimento = conhecimento;
        this.descricao = descricao;
        this.imagem = imagem;
        this.nivel = nivel;
        this.id = id;
        this.tipoConhecimento = tipoConhecimento;
    }

    public String getDescricao_user() {
        return descricao_user;
    }

    public void setDescricao_user(String descricao_user) {
        this.descricao_user = descricao_user;
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

    public TipoConhecimento getTipoConhecimento() {
        return tipoConhecimento;
    }

    public void setTipoConhecimento(TipoConhecimento tipoConhecimento) {
        this.tipoConhecimento = tipoConhecimento;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Integer getNivel() {
        return nivel;
    }

    public void setNivel(Integer nivel) {
        this.nivel = nivel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
