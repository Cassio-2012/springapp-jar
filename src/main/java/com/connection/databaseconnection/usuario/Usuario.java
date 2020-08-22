package com.connection.databaseconnection.usuario;

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
//@Table( name = "usuario", schema = "bdGrupo93a")
public class Usuario {

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer id;
    @Column( name = "nome")
    private String nome;
    @Column( name = "email")
    private String email;
    @Lob
    @Column( name = "photo")
    private String photo;

    @Column( name = "senha")
    private String senha;
    @Column( name = "sobre")
    private String sobre;
    @Column( name = "_local")
    private String local;
    @Column( name = "title")
    private String title;
//    @OneToMany( mappedBy = "usuario")
//    private List<Conhecimento> conhecimentos;

}
