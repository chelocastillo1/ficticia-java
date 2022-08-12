package com.ficticiajava.main.runner;

import com.ficticiajava.main.entity.Article;
import com.ficticiajava.main.repository.ArticleRepository;
import com.ficticiajava.main.repository.AuthorRepository;
import com.ficticiajava.main.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Order(4)
public class ArticleRunner implements CommandLineRunner {
    private final ArticleRepository rArticle;
    private final AuthorRepository rAuthor;
    private final SourceRepository rSource;

    @Autowired
    public ArticleRunner(ArticleRepository rArticle, AuthorRepository rAuthor, SourceRepository rSource) {
        this.rArticle = rArticle;
        this.rAuthor = rAuthor;
        this.rSource = rSource;
    }

    @Override
    public void run(String[] args) throws Exception {
        Article[] listaArticulos = {
                new Article(
                        "ABC",
                        "DEF",
                        "http://www.ficticia.com/",
                        "http://www.ficticia.com/images/img01.jpg",
                        null /*LocalDateTime.of(2022, 5, 5, 10, 20, 30)*/,
                        "GHI",
                        rAuthor.findById(1L).orElse(null),
                        rSource.findById(4L).orElse(null)
                ),
                new Article(
                        "JKL",
                        "MNÑ",
                        "http://www.ficticia.com/",
                        "http://www.ficticia.com/images/img01.jpg",
                        LocalDateTime.of(2022, 6, 6, 9,15,3),
                        "OPQ",
                        rAuthor.findById(4L).orElse(null),
                        rSource.findById(4L).orElse(null)
                ),
                new Article(
                        "RST",
                        "UVW",
                        "http://www.ficticia.com/",
                        "http://www.ficticia.com/images/img01.jpg",
                        LocalDateTime.of(2022, 7, 7,22,11,1),
                        "XYZ",
                        rAuthor.findById(2L).orElse(null),
                        rSource.findById(1L).orElse(null)
                ),
                new Article(
                        "QWERTY",
                        "KJH",
                        "http://www.ficticia.com/",
                        "http://www.ficticia.com/images/img01.jpg",
                        LocalDateTime.of(2022, 8, 8,16,9,3),
                        "POI",
                        rAuthor.findById(3L).orElse(null),
                        rSource.findById(2L).orElse(null)
                ),
        };

        for(Article listaArticulo : listaArticulos)
            rArticle.save(listaArticulo);
    }
}