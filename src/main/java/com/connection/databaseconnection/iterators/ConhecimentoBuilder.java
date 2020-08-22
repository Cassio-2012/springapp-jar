package com.connection.databaseconnection.iterators;

import com.connection.databaseconnection.associative.conhecimento.ConhecimentoUsuario;
import com.connection.databaseconnection.conhecimento.types.TipoConhecimento;
import com.connection.databaseconnection.dto.ConhecimentoUsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConhecimentoBuilder {

    List<ConhecimentoUsuarioDTO> lista;

    public List<ConhecimentoUsuarioDTO> nextList(List<ConhecimentoUsuario> conhecimentoUsuarios) {

        lista = new ArrayList<ConhecimentoUsuarioDTO>();

        for(int i = 0 ; i < conhecimentoUsuarios.size(); i ++) {

            Integer nivel = conhecimentoUsuarios.get(i).getNivel();
            Long id = conhecimentoUsuarios.get(i).getId();
            String descricao_user = conhecimentoUsuarios.get(i).getDescricao_user();
            String imagem = conhecimentoUsuarios.get(i).getConhecimento().getImagem();
            TipoConhecimento tipo = conhecimentoUsuarios.get(i).getConhecimento().getTipo();
            String descricao = conhecimentoUsuarios.get(i).getConhecimento().getDescricao();
            String conhecimento = conhecimentoUsuarios.get(i).getConhecimento().getConhecimento();

            ConhecimentoUsuarioDTO novoConhecimento = new ConhecimentoUsuarioDTO(descricao_user,conhecimento, descricao
            , imagem, nivel,id, tipo);

            lista.add(novoConhecimento);

        }

        return lista;

    }
}
