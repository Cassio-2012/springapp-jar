package com.connection.databaseconnection.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Post, Long> {
//
//    @Query(value = " select p.id from Posts p limit 1 order by p.id desc ")
//    Integer findFirst();

//    @Query(value = " from Posts p order by p.id desc limit 5 ").SetMa
//    List<Posts>primeiraChamada();
//
//    List<Posts> posts

//    @Query(value = " from Posts p where p.id < :number order by p.id desc limit 5  ")
//    List<Posts>demaisChamadas( @Param("number") Integer id);
//
//
}
