package com.connection.databaseconnection.conhecimento;
import com.connection.databaseconnection.conhecimento.types.TipoConhecimento;
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
//@Table(name = "conhecimento", schema="bdGrupo93a")
public class Conhecimento {

    @Id
    @Column( name = "id_conhecimento" )
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id_conhecimento;

    @Column( name = "conhecimento")
    private String conhecimento;

    @Column( name = "descricao")
    private String descricao;

    @Column( name = "tipo")
    @Enumerated(value = EnumType.STRING )
    private TipoConhecimento tipo;

    @Lob
    @Column( name = "imagem")
    private String imagem;

//    @OneToMany(mappedBy = "fkConhecimento" )
//    private List<Possui> usuario;
}
