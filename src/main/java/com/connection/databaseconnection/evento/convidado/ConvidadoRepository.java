package com.connection.databaseconnection.evento.convidado;


import com.connection.databaseconnection.evento.Evento;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface ConvidadoRepository extends CrudRepository<Convidado,String> {
//conf para export arquivo
        Object findAll(Sort nomeConvidado);
        Iterable<Convidado> findByEvento(Evento evento);

}