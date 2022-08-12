package com.ficticiajava.main.repository;

import com.ficticiajava.main.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, PagingAndSortingRepository<Article, Long> {

//    Page<Article> findAllByAuthorFullNameContainsIgnoreCase(String fullName, Pageable pb);
//    List<Article> findAllByTitleContainsOrDescriptionContainsOrContentContainsOrAuthorFullNameContainsIgnoreCase(String title, String description, String content, String fullName);
//    Page<Article> findAllByTitleContainsOrDescriptionContainsOrContentContainsOrAuthorFullNameContainsIgnoreCase(String title, String description, String content, String fullName, Pageable pb);

    @Query("SELECT a1 FROM Article a1 JOIN a1.author a2 WHERE a1.publishedAt IS NOT NULL AND (a1.title LIKE %:texto% OR a1.description LIKE %:texto% OR a1.content LIKE %:texto% OR a2.fullName LIKE %:texto%)")
    Page<Article> findAllByTexto(@Param("texto") String texto, Pageable pb);
}