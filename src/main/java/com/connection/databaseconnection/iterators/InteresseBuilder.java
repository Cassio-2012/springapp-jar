package com.connection.databaseconnection.iterators;

import com.connection.databaseconnection.associative.interesse.InteresseUsuario;
import com.connection.databaseconnection.conhecimento.types.TipoConhecimento;
import com.connection.databaseconnection.dto.InteresseUsuarioDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InteresseBuilder {

    List<InteresseUsuarioDTO> lista;

    public List<InteresseUsuarioDTO> nextList(List<InteresseUsuario> interesseUsuarios) {

        lista = new ArrayList<InteresseUsuarioDTO>();

        for(int i = 0 ; i < interesseUsuarios.size(); i ++) {

            String descricao_interesse = interesseUsuarios.get(i).getDescricao_interesse();
            String imagem = interesseUsuarios.get(i).getConhecimento().getImagem();
            Long id = interesseUsuarios.get(i).getId();
            TipoConhecimento tipo = interesseUsuarios.get(i).getConhecimento().getTipo();
            String descricao = interesseUsuarios.get(i).getConhecimento().getDescricao();
            String conhecimento = interesseUsuarios.get(i).getConhecimento().getConhecimento();

            InteresseUsuarioDTO novoConhecimento = new InteresseUsuarioDTO(descricao_interesse,conhecimento,
                    descricao,imagem, id, tipo);

            lista.add(novoConhecimento);

        }

        return lista;

    }
}