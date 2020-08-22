package com.connection.databaseconnection.posts;

import com.connection.databaseconnection.associative.reacoes.ReacoesService;
import com.connection.databaseconnection.iterators.Iterator;
import com.connection.databaseconnection.iterators.PostBuilder;
import com.connection.databaseconnection.adapters.PostModel;
import com.connection.databaseconnection.usuario.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostsService {

    @Autowired
    private PostsRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    UserController userController;

    @Autowired
    private ReacoesService reacoesService;

    private Iterator listBuilder;

    private Long rangeAtual;

    private Integer last;

    boolean first;


    @Transactional
    Post novoPost(Post post) {
        repository.save(post);
        return post;
    }

    @Transactional
    public boolean setFirst() {
        List<Long> count = entityManager.createQuery(
                "select count(*) from Post ")
                .getResultList();

        rangeAtual = count.get(0);

        if (rangeAtual <1) {
            return false;
        }

        List<Post> result = entityManager.createQuery(
                "select p from Post p order by p.id desc ")
                .setMaxResults(1)
                .getResultList();

        last = result.get(0).getId();

        first = true;

        return true;

    }

    @Transactional
    List<PostModel> loadFeed() {

        List<Object[]> result;
        List<PostModel> resultFinal = new ArrayList<>();
        List<Integer> only_id;
        Integer interessante, gratidao, inovador, all;

        while (resultFinal.size() < 4) {

            if (rangeAtual > 0) {
                if (first) {
                    result = entityManager.createQuery(
                            "select p.id, p.conteudo, u.nome, p._data, p.isImg, p.imagem, u.photo, u.id from Post p inner join" +
                                    " p.usuario as u where p.id <= :range order by p.id desc ")
                            .setParameter("range", last)
                            .setMaxResults(1)
                            .getResultList();

                    only_id = entityManager.createQuery(
                            "select p.id from Post p where p.id <= :range order by p.id desc ")
                            .setParameter("range", last)
                            .setMaxResults(1)
                            .getResultList();

                    Integer reacao = reacoesService.validarReacao(
                            (long) userController.getCurrentUser().getId(), (long)only_id.get(0));

//                    List<Long> one = entityManager.createQuery(
//                            "select count(*) from Reacoes r where r.id_post = :post and r.tipo = 1 ")
//                            .setParameter("post", (long)only_id.get(0))
//                            .getResultList();
//
//                    List<Long> two = entityManager.createQuery(
//                            "select count(*) from Reacoes r where r.id_post = :post and r.tipo = 2 ")
//                            .setParameter("post", (long)only_id.get(0))
//                            .getResultList();
//
//                    List<Long> three = entityManager.createQuery(
//                            "select count(*) from Reacoes r where r.id_post = :post and r.tipo = 3 ")
//                            .setParameter("post", (long)only_id.get(0))
//                            .getResultList();

                            List<Long> allReact = entityManager.createQuery(
                            "select count(*) from Reacoes r where r.id_post = :post")
                            .setParameter("post", (long)only_id.get(0))
                            .getResultList();


//                    interessante = Math.toIntExact(one.get(0));
//
//                    gratidao = Math.toIntExact(two.get(0));
//
//                    inovador = Math.toIntExact(three.get(0));

                    all = Math.toIntExact(allReact.get(0));

                    resultFinal.add(dataBuilder(result, reacao, all));

                    rangeAtual--;

                    last = resultFinal.get(resultFinal.size() - 1).getId();

                    this.first = false;

                } else {
                    result = entityManager.createQuery(
                            "select p.id, p.conteudo, u.nome, p._data, p.isImg, p.imagem, u.photo, u.id from Post p inner join" +
                                    " p.usuario as u where p.id < :range order by p.id desc ")
                            .setParameter("range", last)
                            .setMaxResults(1)
                            .getResultList();

                    only_id = entityManager.createQuery(
                            "select p.id from Post p where p.id < :range order by p.id desc ")
                            .setParameter("range", last)
                            .setMaxResults(1)
                            .getResultList();

                    Integer reacao = reacoesService.validarReacao((long)
                            userController.getCurrentUser().getId(), (long)only_id.get(0));

//                    List<Long> one = entityManager.createQuery(
//                            "select count(*) from Reacoes r where r.id_post = :post and r.tipo = 1 ")
//                            .setParameter("post", (long)only_id.get(0))
//                            .getResultList();
//
//                    List<Long> two = entityManager.createQuery(
//                            "select count(*) from Reacoes r where r.id_post = :post and r.tipo = 2 ")
//                            .setParameter("post", (long)only_id.get(0))
//                            .getResultList();
//
//                    List<Long> three = entityManager.createQuery(
//                            "select count(*) from Reacoes r where r.id_post = :post and r.tipo = 3 ")
//                            .setParameter("post", (long)only_id.get(0))
//                            .getResultList();

                    List<Long> allReact = entityManager.createQuery(
                            "select count(*) from Reacoes r where r.id_post = :post")
                            .setParameter("post", (long)only_id.get(0))
                            .getResultList();


//                    interessante = Math.toIntExact(one.get(0));
//
//                    gratidao = Math.toIntExact(two.get(0));
//
//                    inovador = Math.toIntExact(three.get(0));

                    all = Math.toIntExact(allReact.get(0));

                    resultFinal.add(dataBuilder(result, reacao, all));

                    rangeAtual--;

                    last = resultFinal.get(resultFinal.size() - 1).getId();
                }

            } else {
                return null;
            }
        }
        return resultFinal;

    }
        public PostModel dataBuilder (List < Object[]>lista, Integer reacao, Integer all){

            PostModel result;

            listBuilder = new PostBuilder(lista, reacao, all);

            result = listBuilder.nextList();

            return result;

        }

    }