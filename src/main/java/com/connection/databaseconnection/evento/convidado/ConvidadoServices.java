package com.connection.databaseconnection.evento.convidado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
public class ConvidadoServices {

        @Autowired
    private ConvidadoRepository repository;

        public List<Convidado> listAll(){
            return (List<Convidado>) repository.findAll(Sort.by("nomeConvidado").ascending());
        }
}
