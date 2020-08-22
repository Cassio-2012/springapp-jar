package com.connection.databaseconnection.iterators;

import com.connection.databaseconnection.associative.conhecimento.ConhecimentoUsuario;
import com.connection.databaseconnection.dto.BuscaDTO;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class BuscaBuilder {

    List<BuscaDTO> lista;

    public List<BuscaDTO> nextList(List<ConhecimentoUsuario> conhecimentoUsuarios) {

        lista = new ArrayList<BuscaDTO>();

        for(int i = 0 ; i < conhecimentoUsuarios.size(); i ++) {

            long nivel = conhecimentoUsuarios.get(i).getNivel();
            long id = conhecimentoUsuarios.get(i).getUsuario().getId();
            String nome = conhecimentoUsuarios.get(i).getUsuario().getNome();
            String local = conhecimentoUsuarios.get(i).getUsuario().getLocal();
            String title = conhecimentoUsuarios.get(i).getUsuario().getTitle();
            String conhecimento = conhecimentoUsuarios.get(i).getConhecimento().getConhecimento();
            String email = conhecimentoUsuarios.get(i).getUsuario().getEmail();
            String imagem = conhecimentoUsuarios.get(i).getUsuario().getPhoto();

            BuscaDTO novaBusca = new BuscaDTO(id,nivel,nome, email,imagem,conhecimento,local,title);

            lista.add(novaBusca);

        }



        return lista;

    }
}
