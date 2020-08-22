package com.connection.databaseconnection.evento;

import com.connection.databaseconnection.evento.convidado.Convidado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;

    private String nome;

    private String cep;

    private String logradouro;

    private String bairro;

    private String localidade;

    private String uf;

    private String complemento;

    @Column(name = "_data")
    private String dataEvento;

    private String horario;

    private String descricao;

    @OneToMany
    private List<Convidado> convidados;
    private Integer adm;
}