//package com.connection.databaseconnection.adapters;
//
//import com.connection.databaseconnection.associative.reacoes.ReacoesService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class PostAdapter {
//
//    List<PostModel> listAdapt;
//
//    @Autowired
//    private ReacoesService reacoesController;
//
//    public PostAdapter() {
//        this.listAdapt = new ArrayList<PostModel>();
//    }
//
//    public void setModel(Integer id, String nome, String conteudo, String date, String imagem,
//                         Integer id_user, Integer reacao) {
//
//        PostModel newPost = new PostModel(id,id_user,nome,conteudo,imagem,date, reacao);
//
//        listAdapt.add(newPost);
//
//    }
//
//    public List<PostModel> getModel() {
//        if(listAdapt.isEmpty()) {
//            return null;
//        }
//        else {
//            return listAdapt;
//        }
//    }
//
//}
