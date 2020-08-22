package com.connection.databaseconnection.posts;

import com.connection.databaseconnection.adapters.PostModel;
import com.connection.databaseconnection.dto.PostDTO;
import com.connection.databaseconnection.exception.ErroConexao;
import com.connection.databaseconnection.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@Controller
@RestController
@RequestMapping("/post")
public class PostsController {

    @Autowired
    private PostsService controller;


    public  PostsController(PostsService controller) {
        this.controller = controller;
    }


    @PostMapping("/new")
    public ResponseEntity novoPost(@RequestBody PostDTO postDTO) {

        Usuario newPostUser = Usuario.builder().id(postDTO.getId_user()).build();


        Post post = Post.builder().conteudo(postDTO.getConteudo())._data(postDTO.get_data())
                .usuario(newPostUser).isImg(postDTO.getIsImg()).imagem(postDTO.getImagem()).build();
        try{

            Post postEnviado = controller.novoPost(post);
            return new ResponseEntity(postEnviado, HttpStatus.CREATED);
        }catch (ErroConexao e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/load/initial")
    public ResponseEntity loadPostsSet() {

        try{
            if (controller.setFirst()) {
                return new ResponseEntity(HttpStatus.OK);
            } else {
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            }
        }catch (ErroConexao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/load/feed")

    public ResponseEntity loadPosts() {

        try{
            List<PostModel> lista =  controller.loadFeed();
            if (lista == null) {
           return new ResponseEntity("A lista está vazia", HttpStatus.ACCEPTED);
            }
            return new ResponseEntity(lista, HttpStatus.OK);
        }catch (ErroConexao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
