package com.connection.databaseconnection.posts;



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
//@Table(name = "post", schema="bdGrupo93a")
public class Post {

    @Id
    @Column( name = "id" )
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column( name = "conteudo")
    private String conteudo;

    @ManyToOne
    @JoinColumn( name = "id_user")
    private Usuario usuario;

    @Column( name = "_data")
    private String _data;

    @Column( name = "is_img")
    private Integer isImg;

    @Lob
    @Column( name = "imagem")
    private String imagem;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
