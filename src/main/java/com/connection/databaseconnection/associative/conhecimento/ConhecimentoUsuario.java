package com.connection.databaseconnection.associative.conhecimento;

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
//@Table( name = "conhecimento_usuario", schema = "bdGrupo93a")
public class ConhecimentoUsuario {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    @Column( name = "id")
    private Long id;

    @Column( name = "descricao_user")
    private String descricao_user;

    @Column( name = "nivel" )
    private int nivel;

    @ManyToOne
    @JoinColumn( name = "fk_conhecimento")
    private Conhecimento conhecimento;

    @ManyToOne
    @JoinColumn( name = "fk_usuario")
    private Usuario usuario;

}
