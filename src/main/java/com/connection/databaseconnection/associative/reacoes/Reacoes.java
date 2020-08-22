package com.connection.databaseconnection.associative.reacoes;

import com.connection.databaseconnection.conhecimento.Conhecimento;
import com.connection.databaseconnection.posts.Post;
import com.connection.databaseconnection.usuario.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table( name = "reacoes", schema = "bdGrupo93a")
public class Reacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "tipo")
    private Integer tipo;

    @Column(name = "id_user")
    private Long id_user;

    @Column(name = "id_post")
    private Long id_post;
}
