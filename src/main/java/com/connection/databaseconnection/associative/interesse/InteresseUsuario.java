package com.connection.databaseconnection.associative.interesse;

import com.connection.databaseconnection.conhecimento.Conhecimento;
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
//@Table( name = "interesse_usuario", schema = "bdGrupo93a")
public class InteresseUsuario {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "id")
    private Long id;

    @Column( name = "descricao_interesse")
    private String descricao_interesse;

    @ManyToOne
    @JoinColumn( name = "fk_conhecimento")
    private Conhecimento conhecimento;

    @ManyToOne
    @JoinColumn( name = "fk_user")
    private Usuario usuario;

}