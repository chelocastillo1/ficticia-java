package com.ficticiajava.main.service;

import com.ficticiajava.main.converter.ArticleConverter;
import com.ficticiajava.main.converter.AuthorConverter;
import com.ficticiajava.main.converter.SourceConverter;
import com.ficticiajava.main.dto.ArticleDto;
import com.ficticiajava.main.entity.Article;
import com.ficticiajava.main.entity.Author;
import com.ficticiajava.main.entity.Source;
import com.ficticiajava.main.repository.ArticleRepository;
import com.ficticiajava.main.repository.AuthorRepository;
import com.ficticiajava.main.repository.SourceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository repository;

    @Mock
    private AuthorRepository repositoryAuthor;

    @Mock
    private SourceRepository repositorySource;

    @InjectMocks
    private ArticleService service;

    @Test
    void createArticle_happyRoad() {
        final Long nArticleId = 78L;

        final Article articleTemp = new Article(
                "Argentina llega a su más bajo índice de corrupción hasta la fecha",
                "En la fecha de hoy, la empresa consultora MentiritisAguda SRL ha arrojado unos resultados que demuestran el progreso favorable hacia un estado -cero corrupción-.",
                "https://www.fake-news.com.ar/post/estado-cero-corrupcion/",
                "https://img.fake-news.com.ar/post/thumbnails/estado-cero-corrupcion.jpg",
                LocalDateTime.of(2018,12,9,21,12,18),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec maximus, nunc egestas sodales varius, ante eros condimentum sem, pharetra vehicula augue nibh non odio. Quisque non maximus enim. Pellentesque id mattis elit, at euismod velit. Duis et magna scelerisque, pharetra metus in, congue mi. Curabitur aliquet nibh in fringilla porttitor. Suspendisse potenti. Integer nulla ligula, iaculis vitae dui quis, molestie viverra tortor. Sed dictum dignissim ex sit amet pretium. In volutpat posuere luctus. Quisque et leo felis. Proin in tellus magna. Morbi non leo vel neque placerat faucibus. Praesent feugiat porta ultricies. Nulla euismod mauris quis mattis euismod. Donec ornare mi ut lorem dignissim, sit amet iaculis neque varius. Praesent sapien sapien, malesuada ut commodo vel, tempus quis tellus.",
                null,
                null
        );

        // When: Registra un Article
        when(repository.save(articleTemp))
                .thenReturn(new Article(
                                nArticleId,
                                "Argentina llega a su más bajo índice de corrupción hasta la fecha",
                                "En la fecha de hoy, la empresa consultora MentiritisAguda SRL ha arrojado unos resultados que demuestran el progreso favorable hacia un estado -cero corrupción-.",
                                "https://www.fake-news.com.ar/post/estado-cero-corrupcion/",
                                "https://img.fake-news.com.ar/post/thumbnails/estado-cero-corrupcion.jpg",
                                LocalDateTime.of(2018,12,9,21,12,18),
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec maximus, nunc egestas sodales varius, ante eros condimentum sem, pharetra vehicula augue nibh non odio. Quisque non maximus enim. Pellentesque id mattis elit, at euismod velit. Duis et magna scelerisque, pharetra metus in, congue mi. Curabitur aliquet nibh in fringilla porttitor. Suspendisse potenti. Integer nulla ligula, iaculis vitae dui quis, molestie viverra tortor. Sed dictum dignissim ex sit amet pretium. In volutpat posuere luctus. Quisque et leo felis. Proin in tellus magna. Morbi non leo vel neque placerat faucibus. Praesent feugiat porta ultricies. Nulla euismod mauris quis mattis euismod. Donec ornare mi ut lorem dignissim, sit amet iaculis neque varius. Praesent sapien sapien, malesuada ut commodo vel, tempus quis tellus.",
                                null,
                                null
                        )
                );

        // When: Busca un Article para verificar si existe o no uno idéntico en la base de datos.
        when(repository.findOne(Example.of(articleTemp)))
                .thenReturn(Optional.empty());

        // Crear entity
        ArticleDto dtoArticle = service.create(ArticleConverter.toDto(articleTemp));

        // Afirmaciones para Article (solo)
        assertNotNull(dtoArticle);
        assertTrue(dtoArticle.getId() > 0);
        assertNull(dtoArticle.getAuthor());
        assertNull(dtoArticle.getSource());
        assertEquals(articleTemp.getTitle(), dtoArticle.getTitle());
    }

    @Test
    void setAutor_happyRoad() {
        final Long nArticleId = 78L;
        final Long nAuthorId = 100L;

        final Author authorTemp = new Author(
                nAuthorId,
                "Gasparin",
                "Ghost",
                LocalDate.of(2017, 1 ,10)
        );

        // When: Busca un Author para luego asociarlo un Article.
        when(repositoryAuthor.findById(nAuthorId))
                .thenReturn(Optional.of(authorTemp));

        // When: Busca un Article para luego asociarle un Author.
        when(repository.findById(nArticleId))
                .thenReturn(Optional.of(new Article(
                        nArticleId,
                        "Argentina llega a su más bajo índice de corrupción hasta la fecha",
                        "En la fecha de hoy, la empresa consultora MentiritisAguda SRL ha arrojado unos resultados que demuestran el progreso favorable hacia un estado -cero corrupción-.",
                        "https://www.fake-news.com.ar/post/estado-cero-corrupcion/",
                        "https://img.fake-news.com.ar/post/thumbnails/estado-cero-corrupcion.jpg",
                        LocalDateTime.of(2018, 12, 9, 21, 12, 18),
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec maximus, nunc egestas sodales varius, ante eros condimentum sem, pharetra vehicula augue nibh non odio. Quisque non maximus enim. Pellentesque id mattis elit, at euismod velit. Duis et magna scelerisque, pharetra metus in, congue mi. Curabitur aliquet nibh in fringilla porttitor. Suspendisse potenti. Integer nulla ligula, iaculis vitae dui quis, molestie viverra tortor. Sed dictum dignissim ex sit amet pretium. In volutpat posuere luctus. Quisque et leo felis. Proin in tellus magna. Morbi non leo vel neque placerat faucibus. Praesent feugiat porta ultricies. Nulla euismod mauris quis mattis euismod. Donec ornare mi ut lorem dignissim, sit amet iaculis neque varius. Praesent sapien sapien, malesuada ut commodo vel, tempus quis tellus.",
                        new Author(
                                "Juan",
                                "Perez",
                                LocalDate.of(2018, 10 ,10)
                        ),
                        null
                )));

        // When: Registra un Article
        when(repository.save(
                new Article(
                        nArticleId,
                        "Argentina llega a su más bajo índice de corrupción hasta la fecha",
                        "En la fecha de hoy, la empresa consultora MentiritisAguda SRL ha arrojado unos resultados que demuestran el progreso favorable hacia un estado -cero corrupción-.",
                        "https://www.fake-news.com.ar/post/estado-cero-corrupcion/",
                        "https://img.fake-news.com.ar/post/thumbnails/estado-cero-corrupcion.jpg",
                        LocalDateTime.of(2018, 12, 9, 21, 12, 18),
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec maximus, nunc egestas sodales varius, ante eros condimentum sem, pharetra vehicula augue nibh non odio. Quisque non maximus enim. Pellentesque id mattis elit, at euismod velit. Duis et magna scelerisque, pharetra metus in, congue mi. Curabitur aliquet nibh in fringilla porttitor. Suspendisse potenti. Integer nulla ligula, iaculis vitae dui quis, molestie viverra tortor. Sed dictum dignissim ex sit amet pretium. In volutpat posuere luctus. Quisque et leo felis. Proin in tellus magna. Morbi non leo vel neque placerat faucibus. Praesent feugiat porta ultricies. Nulla euismod mauris quis mattis euismod. Donec ornare mi ut lorem dignissim, sit amet iaculis neque varius. Praesent sapien sapien, malesuada ut commodo vel, tempus quis tellus.",
                        authorTemp,
                        null
                )))
                .thenReturn(
                        new Article(
                                nArticleId,
                                "Argentina llega a su más bajo índice de corrupción hasta la fecha",
                                "En la fecha de hoy, la empresa consultora MentiritisAguda SRL ha arrojado unos resultados que demuestran el progreso favorable hacia un estado -cero corrupción-.",
                                "https://www.fake-news.com.ar/post/estado-cero-corrupcion/",
                                "https://img.fake-news.com.ar/post/thumbnails/estado-cero-corrupcion.jpg",
                                LocalDateTime.of(2018,12,9,21,12,18),
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec maximus, nunc egestas sodales varius, ante eros condimentum sem, pharetra vehicula augue nibh non odio. Quisque non maximus enim. Pellentesque id mattis elit, at euismod velit. Duis et magna scelerisque, pharetra metus in, congue mi. Curabitur aliquet nibh in fringilla porttitor. Suspendisse potenti. Integer nulla ligula, iaculis vitae dui quis, molestie viverra tortor. Sed dictum dignissim ex sit amet pretium. In volutpat posuere luctus. Quisque et leo felis. Proin in tellus magna. Morbi non leo vel neque placerat faucibus. Praesent feugiat porta ultricies. Nulla euismod mauris quis mattis euismod. Donec ornare mi ut lorem dignissim, sit amet iaculis neque varius. Praesent sapien sapien, malesuada ut commodo vel, tempus quis tellus.",
                                authorTemp,
                                null
                        )
                );

        // Vincular Author con el Article.
        ArticleDto dtoArticleWithAuthor = service.setAuthor(nArticleId, nAuthorId);

        // Afirmaciones para Article (ya con referencia a un Author)
        assertNotNull(dtoArticleWithAuthor);
        assertTrue(dtoArticleWithAuthor.getId() > 0);
        assertNotNull(dtoArticleWithAuthor.getAuthor());
        assertTrue(dtoArticleWithAuthor.getAuthor().getId() > 0);
        assertEquals(AuthorConverter.toDto(authorTemp), dtoArticleWithAuthor.getAuthor());
    }

    @Test
    void setSource_happyRoad() {
        final Long nArticleId = 78L;
        final Long nSourceId = 13L;

        final Source SourceTemp = new Source(
                "Fake News",
                "Canal de noticias Argentinas",
                LocalDate.of(2003, 1 , 1)
        );

        // When: Busca un Source para luego asociarlo un Article.
        when(repositorySource.findById(nSourceId))
                .thenReturn(Optional.of(SourceTemp));

        // When: Busca un Article para luego asociarle un Source.
        when(repository.findById(nArticleId))
                .thenReturn(Optional.of(new Article(
                        nArticleId,
                        "Argentina llega a su más bajo índice de corrupción hasta la fecha",
                        "En la fecha de hoy, la empresa consultora MentiritisAguda SRL ha arrojado unos resultados que demuestran el progreso favorable hacia un estado -cero corrupción-.",
                        "https://www.fake-news.com.ar/post/estado-cero-corrupcion/",
                        "https://img.fake-news.com.ar/post/thumbnails/estado-cero-corrupcion.jpg",
                        LocalDateTime.of(2018, 12, 9, 21, 12, 18),
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec maximus, nunc egestas sodales varius, ante eros condimentum sem, pharetra vehicula augue nibh non odio. Quisque non maximus enim. Pellentesque id mattis elit, at euismod velit. Duis et magna scelerisque, pharetra metus in, congue mi. Curabitur aliquet nibh in fringilla porttitor. Suspendisse potenti. Integer nulla ligula, iaculis vitae dui quis, molestie viverra tortor. Sed dictum dignissim ex sit amet pretium. In volutpat posuere luctus. Quisque et leo felis. Proin in tellus magna. Morbi non leo vel neque placerat faucibus. Praesent feugiat porta ultricies. Nulla euismod mauris quis mattis euismod. Donec ornare mi ut lorem dignissim, sit amet iaculis neque varius. Praesent sapien sapien, malesuada ut commodo vel, tempus quis tellus.",
                        null,
                        new Source(
                                nSourceId,
                                "Global News",
                                "All news in the world.",
                                LocalDate.of(1893, 7 , 5)
                        )
                )));

        // When: Registra un Article
        when(repository.save(
                new Article(
                        nArticleId,
                        "Argentina llega a su más bajo índice de corrupción hasta la fecha",
                        "En la fecha de hoy, la empresa consultora MentiritisAguda SRL ha arrojado unos resultados que demuestran el progreso favorable hacia un estado -cero corrupción-.",
                        "https://www.fake-news.com.ar/post/estado-cero-corrupcion/",
                        "https://img.fake-news.com.ar/post/thumbnails/estado-cero-corrupcion.jpg",
                        LocalDateTime.of(2018, 12, 9, 21, 12, 18),
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec maximus, nunc egestas sodales varius, ante eros condimentum sem, pharetra vehicula augue nibh non odio. Quisque non maximus enim. Pellentesque id mattis elit, at euismod velit. Duis et magna scelerisque, pharetra metus in, congue mi. Curabitur aliquet nibh in fringilla porttitor. Suspendisse potenti. Integer nulla ligula, iaculis vitae dui quis, molestie viverra tortor. Sed dictum dignissim ex sit amet pretium. In volutpat posuere luctus. Quisque et leo felis. Proin in tellus magna. Morbi non leo vel neque placerat faucibus. Praesent feugiat porta ultricies. Nulla euismod mauris quis mattis euismod. Donec ornare mi ut lorem dignissim, sit amet iaculis neque varius. Praesent sapien sapien, malesuada ut commodo vel, tempus quis tellus.",
                        null,
                        SourceTemp
                )))
                .thenReturn(
                        new Article(
                                nArticleId,
                                "Argentina llega a su más bajo índice de corrupción hasta la fecha",
                                "En la fecha de hoy, la empresa consultora MentiritisAguda SRL ha arrojado unos resultados que demuestran el progreso favorable hacia un estado -cero corrupción-.",
                                "https://www.fake-news.com.ar/post/estado-cero-corrupcion/",
                                "https://img.fake-news.com.ar/post/thumbnails/estado-cero-corrupcion.jpg",
                                LocalDateTime.of(2018,12,9,21,12,18),
                                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec maximus, nunc egestas sodales varius, ante eros condimentum sem, pharetra vehicula augue nibh non odio. Quisque non maximus enim. Pellentesque id mattis elit, at euismod velit. Duis et magna scelerisque, pharetra metus in, congue mi. Curabitur aliquet nibh in fringilla porttitor. Suspendisse potenti. Integer nulla ligula, iaculis vitae dui quis, molestie viverra tortor. Sed dictum dignissim ex sit amet pretium. In volutpat posuere luctus. Quisque et leo felis. Proin in tellus magna. Morbi non leo vel neque placerat faucibus. Praesent feugiat porta ultricies. Nulla euismod mauris quis mattis euismod. Donec ornare mi ut lorem dignissim, sit amet iaculis neque varius. Praesent sapien sapien, malesuada ut commodo vel, tempus quis tellus.",
                                null,
                                SourceTemp
                        )
                );

        // Vincular Source con el Article.
        ArticleDto dtoArticleWithSource = service.setSource(nArticleId, nSourceId);

        // Afirmaciones para Article (ya con referencia a un Source)
        assertNotNull(dtoArticleWithSource);
        assertTrue(dtoArticleWithSource.getId() > 0);
        assertNotNull(dtoArticleWithSource.getSource());
        assertTrue(dtoArticleWithSource.getSource().getId() > 0);
        assertEquals(SourceConverter.toDto(SourceTemp), dtoArticleWithSource.getSource());
    }
}