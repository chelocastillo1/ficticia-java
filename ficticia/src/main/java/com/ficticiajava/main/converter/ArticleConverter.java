package com.ficticiajava.main.converter;

import com.ficticiajava.main.dto.ArticleDto;
import com.ficticiajava.main.entity.Article;
import org.springframework.stereotype.Component;

@Component
public final class ArticleConverter {

    /**
     * Permite mapear de objeto DTO a ENTITY.
     * @param dto Objeto DTO a convertir.
     * @return Devuelve el objeto ENTITY convertido.
     **/
    public static Article toEntity(ArticleDto dto) {
        return new Article(
                dto.getId(),
                dto.getTitle(),
                dto.getDescription(),
                dto.getUrl(),
                dto.getUrlToImage(),
                dto.getPublishedAt(),
                dto.getContent(),
                dto.getAuthor() != null ? AuthorConverter.toEntity(dto.getAuthor()) : null,
                dto.getSource() != null ? SourceConverter.toEntity(dto.getSource()) : null
        );
    }

    /**
     * Permite mapear de objeto ENTITY a DTO.
     * @param e Objeto ENTITY a convertir.
     * @return Devuelve el objeto ArticleDto convertido.
     **/
    public static ArticleDto toDto(Article e) {
        return new ArticleDto(
                e.getId(),
                e.getTitle(),
                e.getDescription(),
                e.getUrl(),
                e.getUrlToImage(),
                e.getPublishedAt(),
                e.getContent(),
                e.getAuthor() != null ? AuthorConverter.toDto(e.getAuthor()) : null,
                e.getSource() != null ? SourceConverter.toDto(e.getSource()) : null
        );
    }
}