package com.connection.databaseconnection.associative.conhecimento;

import com.connection.databaseconnection.conhecimento.types.TipoConhecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConhecimentoUsuarioRepository extends JpaRepository<ConhecimentoUsuario, Long> {

    @Query(" select c from ConhecimentoUsuario c left join fetch c.conhecimento uk" +
            "left join fetch c.usuario u where u.id = :param ")
    List<ConhecimentoUsuario>  findConhecimentoById( @Param("param") Integer id);

    @Query(" select c from ConhecimentoUsuario c left join fetch c.usuario u" +
            "left join fetch c.conhecimento uk where uk.id_conhecimento = :param ")
    List<ConhecimentoUsuario> findByIdKnowId(@Param("param")long id_conhecimento);

    @Query(" select c from ConhecimentoUsuario c left join fetch c.usuario u" +
            "left join fetch c.conhecimento uk where c.nivel = :param ")
    List<ConhecimentoUsuario> findByLevel(@Param("param")int level);

    @Query(" select c from ConhecimentoUsuario c left join fetch c.usuario u" +
            "left join fetch c.conhecimento uk where c.nivel = ?1 and uk.conhecimento" +
            " like " + "%" + "?2" + "%")
    List<ConhecimentoUsuario> findByConhecimentoandLevel(Integer nivel, String con);

    @Query(" select c from ConhecimentoUsuario c left join fetch c.usuario u" +
            "left join fetch c.conhecimento uk where c.nivel >= ?1 and uk.tipo" +
            " = ?2")
    List<ConhecimentoUsuario> findByLevelandType(int level, TipoConhecimento tipo);
}


