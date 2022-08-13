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
        final String[] content = { // Lorem ipsum...
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus feugiat dolor lacus, sit amet aliquam risus sollicitudin nec.",
                "Suspendisse rutrum eget diam vel tempor. Nam hendrerit eu augue eget sollicitudin. Aliquam ac molestie libero.",
                "Praesent ultrices ipsum non eros dictum, at porta leo iaculis. Fusce lacinia justo ante, sed blandit leo pulvinar nec. Duis vel purus risus.",
                "Ut eleifend dolor vitae erat aliquet lacinia. Cras pulvinar vulputate scelerisque. Phasellus euismod pharetra fermentum.",
                "Aenean vel suscipit diam. Maecenas sed tellus consectetur, elementum enim eget, ultricies nunc. Sed sit amet sodales eros.",
                "Quisque posuere nulla id orci lobortis luctus. Donec mattis tincidunt erat, vel luctus ipsum sodales vel. Nam ultricies venenatis sem, eget accumsan elit rhoncus at.",
                "Morbi vitae elit erat. Suspendisse vitae odio tempor, tempus massa sit amet, accumsan neque. Aenean purus elit, eleifend id pellentesque vitae, convallis ut elit.",
                "Donec pellentesque, sem in venenatis viverra, mauris nisi pulvinar tortor, in vehicula justo lorem eget velit. Maecenas ut ex ornare neque viverra tincidunt at eu ligula.",
                "Quisque eu risus sapien. Donec sed suscipit erat, eu vulputate sapien. Aliquam ac semper nisl, ut condimentum nunc. Fusce tortor sapien, pharetra sit amet tortor ac, egestas rutrum ligula.",
                "Ut at commodo diam. Integer mauris quam, aliquet sit amet efficitur vitae, tempor ac augue. ",
                "Maecenas nec pharetra lectus, id vehicula enim. Cras suscipit egestas maximus. Aenean et ante erat. Etiam ultricies pretium eros, id rutrum purus faucibus id.",
                "Duis lacus risus, molestie vel tincidunt ac, maximus eget tortor. Proin ullamcorper porta ligula, vel blandit nulla bibendum ac.",
                "Praesent sed placerat mi, a faucibus nibh. Nam ac molestie nibh. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos.",
                "Nullam sollicitudin lobortis neque, ut venenatis magna volutpat tincidunt. Mauris ac facilisis lectus, eu aliquet tellus.",
                "Quisque sodales urna est, cursus pellentesque turpis posuere ac. Donec ultrices velit neque, at commodo nunc mattis consectetur.",
                "Maecenas in pulvinar augue. Integer tincidunt mi vel massa ornare, pulvinar tristique magna imperdiet. Fusce at suscipit dui, id molestie neque.",
                "Sed ante arcu, sodales in nisl in, consequat lobortis odio. Proin placerat nisl orci, ornare ullamcorper eros imperdiet ut. Donec fermentum mollis elit vitae malesuada.",
                "In accumsan nisl rhoncus tellus iaculis eleifend. Phasellus ac porttitor lorem.",
                "Aenean sit amet urna leo. Aenean efficitur arcu quis sollicitudin faucibus. Morbi sodales elementum lacus, quis tincidunt nunc fringilla sit amet.",
                "Integer bibendum dui leo, quis maximus purus maximus sed. Cras porta, ex et elementum bibendum, lectus leo molestie mi, at egestas ipsum arcu a nunc.",
                "Phasellus at pellentesque neque, sed ornare risus. Phasellus ornare in diam sed tristique. Nullam fermentum neque tellus, pretium cursus dolor molestie at."
        };

        for(int i = 1; i <= 20; i++) {
            String strAux = String.format("%02d", i);

            rArticle.save(
                    new Article(
                            String.format("Noticia A%s", strAux),
                            String.format("Descripción de la noticia A%s.", strAux),
                            String.format("https://news.ficticia.com/noticia-a%s/", strAux),
                            String.format("https://thumbnails.ficticia.com/noticia-a%s/title.jpg", strAux),
                            LocalDateTime.of(
                                    2021,
                                    (int) (Math.random() * 12 + 1),
                                    (int) (Math.random() * 28 + 1),
                                    (int) (Math.random() * 23 + 0),
                                    (int) (Math.random() * 59 + 0),
                                    (int) (Math.random() * 59 + 0)
                            ),
                            content[i - 1],
                            rAuthor.findById((long) (int) (Math.random() * 14 + 1)).orElse(null),
                            rSource.findById((long) (int) (Math.random() * 7 + 1)).orElse(null)
                    )
            );
        }
    }
}