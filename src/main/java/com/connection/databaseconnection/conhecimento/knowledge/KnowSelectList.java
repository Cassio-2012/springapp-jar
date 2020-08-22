package com.connection.databaseconnection.conhecimento.knowledge;

import com.connection.databaseconnection.conhecimento.Conhecimento;
import com.connection.databaseconnection.conhecimento.ConhecimentoService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;


public class KnowSelectList {

    @Autowired
    private ConhecimentoService conhecimentoService;

    private List<KnowledgeModel> lista;

    public KnowSelectList() {
        lista = new ArrayList<>();
    }

    public List<KnowledgeModel> getConhecimentos(List<Conhecimento> all) {

        for(Conhecimento c : all) {
            KnowledgeModel novo = new KnowledgeModel(c.getId_conhecimento(),c.getConhecimento());
            lista.add(novo);
        }

        return lista;

    }

}
