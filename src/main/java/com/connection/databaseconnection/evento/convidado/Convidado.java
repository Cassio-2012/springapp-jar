package com.connection.databaseconnection.evento.convidado;

import com.connection.databaseconnection.evento.Evento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Convidado {
 

    @Id
    @GeneratedValue
    @Column(name = "id",length = 4)
    private Long id;
    @Column( name = "nome_convidado",length = 45)
    private String nomeConvidado;
    @Column(length =  100)
    private String email;
    @ManyToOne
    @JoinColumn( name = "evento_codigo")
    private Evento evento;



}
