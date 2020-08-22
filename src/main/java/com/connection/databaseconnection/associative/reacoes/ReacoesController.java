package com.connection.databaseconnection.associative.reacoes;

import com.connection.databaseconnection.dto.ReacoesDTO;
import com.connection.databaseconnection.exception.ErroConexao;
import com.connection.databaseconnection.posts.Post;
import com.connection.databaseconnection.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reacoes")
public class ReacoesController {

    @Autowired
    private ReacoesService controller;

    @PostMapping("/reagir")
    public ResponseEntity reagirInteresante(@RequestBody ReacoesDTO dto){

        Reacoes novaReacao = Reacoes.builder().tipo(dto.getTipo())
                .id_user(dto.getId_user()).id_post(dto.getId_post()).build();
        try{
            boolean salvo = controller.salvarReacao(novaReacao);

            if(salvo) {
                return new ResponseEntity(HttpStatus.CREATED);
            }

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }catch (ErroConexao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/validar/{user}/{post}")
    public ResponseEntity teste(@PathVariable("user") Integer user,
                                    @PathVariable("post") Integer post) {

        try{
           Integer result = controller.validarReacao(user, post);

            return ResponseEntity.ok(result);
        }catch (ErroConexao e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}


