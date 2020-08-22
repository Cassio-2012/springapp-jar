package com.connection.databaseconnection.associative.reacoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReacoesRepository extends JpaRepository<Reacoes, Long> {

    @Query(" select r from Reacoes r where r.id_user = ?1 and r.id_post = ?2")
    List<Reacoes> validaReacao(long usuario, long post);

}


