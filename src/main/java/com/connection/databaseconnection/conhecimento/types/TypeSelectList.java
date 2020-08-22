package com.connection.databaseconnection.conhecimento.types;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeSelectList {

    private List<TypeModel> tipos;

    public TypeSelectList() {
        this.tipos = new ArrayList();;
    }

    public List<TypeModel> getTipos() {
        TypeModel selecione = new TypeModel("", "Selecione");
        TypeModel programacao = new TypeModel("PROGRAMACAO", "Programação");
        TypeModel infra = new TypeModel("INFRAESTRUTURA", "Infraestrutura");
        TypeModel dados = new TypeModel("DADOS", "Dados");

        TypeModel design = new TypeModel("DESIGN", "Design");
        TypeModel testes = new TypeModel("TESTES", "Testes");
        TypeModel seg = new TypeModel("SEGURANCA", "Segurança");
        TypeModel gestao = new TypeModel("GESTAO", "Gestão");

        tipos.add(selecione);
        tipos.add(programacao);
        tipos.add(infra);
        tipos.add(dados);
        tipos.add(design);
        tipos.add(testes);
        tipos.add(seg);
        tipos.add(gestao);

        return tipos;
    }

}
