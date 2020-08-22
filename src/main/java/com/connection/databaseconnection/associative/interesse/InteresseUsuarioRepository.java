package com.connection.databaseconnection.associative.interesse;

import com.connection.databaseconnection.associative.conhecimento.ConhecimentoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteresseUsuarioRepository extends JpaRepository<InteresseUsuario, Long> {

    @Query(" select i from InteresseUsuario i left join fetch i.conhecimento uk" +
            "left join fetch i.usuario u where u.id = :param ")
    List<InteresseUsuario>  findConhecimentoById( @Param("param") Integer id);
}
