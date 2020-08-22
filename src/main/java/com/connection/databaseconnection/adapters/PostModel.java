package com.connection.databaseconnection.adapters;

public class PostModel {

    private Integer id, id_user, reacao, total, isImg;
    private String nome, conteudo, imagem , data, img_conteudo;


    public PostModel(Integer id, Integer id_user, Integer reacao, Integer total, String nome,
                     String conteudo, String imagem, String data, String img_conteudo, Integer isImg) {
        this.id = id;
        this.id_user = id_user;
        this.reacao = reacao;
        this.nome = nome;
        this.conteudo = conteudo;
        this.imagem = imagem;
        this.data = data;
        this.total = total;
        this.img_conteudo = img_conteudo;
        this.isImg = isImg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getReacao() {
        return reacao;
    }

    public void setReacao(Integer reacao) {
        this.reacao = reacao;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getImg_conteudo() {
        return img_conteudo;
    }

    public void setImg_conteudo(String img_conteudo) {
        this.img_conteudo = img_conteudo;
    }


    public Integer getIsImg() {
        return isImg;
    }

    public void setIsImg(Integer isImg) {
        this.isImg = isImg;
    }
}
