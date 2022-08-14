package com.ficticiajava.main.service;

import com.ficticiajava.main.converter.ArticleConverter;
import com.ficticiajava.main.dto.ArticleDto;
import com.ficticiajava.main.dto.ResponseResultsDto;
import com.ficticiajava.main.entity.Article;
import com.ficticiajava.main.entity.Author;
import com.ficticiajava.main.entity.Source;
import com.ficticiajava.main.exception.ConstraintViolationException;
import com.ficticiajava.main.exception.PageNotFoundException;
import com.ficticiajava.main.global.Constants;
import com.ficticiajava.main.global.Pagination;
import com.ficticiajava.main.repository.ArticleRepository;
import com.ficticiajava.main.repository.AuthorRepository;
import com.ficticiajava.main.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    private final ArticleRepository repository;

    private final AuthorRepository repositoryAuthor;

    private final SourceRepository repositorySource;


    @Autowired
    public ArticleService(ArticleRepository repository, AuthorRepository repositoryAuthor, SourceRepository repositorySource) {
        this.repository = repository;
        this.repositoryAuthor = repositoryAuthor;
        this.repositorySource = repositorySource;
    }

    public ResponseResultsDto findAll(int nPage) {
        Pageable pb;
        Page<Article> page;
        List<ArticleDto> results;

        pb = PageRequest.of(nPage - 1, Pagination.PAGE_SIZE);
        page = repository.findAllByOrderByPublishedAtDesc(pb);

        if(nPage != 1 && page.getTotalPages() < nPage) // Nro de página fuera de rango
            throw new PageNotFoundException(Constants.EXCEPTION_PAGE_NOT_FOUND, nPage);

        results = page.getContent().stream()
                .map(ArticleConverter::toDto)
                .collect(Collectors.toList());

        return new ResponseResultsDto(
                HttpStatus.OK, // estado HTTP
                page.getTotalElements(), // cantidad de elementos
                page.getTotalPages(), // nro total de páginas
                page.getNumber() + 1, // nro de página actualmente visualizada
                page.getSize(), // cantidad de elementos a mostrar por página
                results // lista con los elementos a mostrar
        );
    }

    public ArticleDto findById(Long nId) {
        return ArticleConverter.toDto(
                repository.findById(nId)
                        .orElseThrow(
                                () -> new EntityNotFoundException(Constants.EXCEPTION_ENTITY_NOT_FOUND)
                        )
        );
    }

    public ResponseResultsDto findAllByText(String strLike, int nPage) {
        Pageable pb;
        Page<Article> page;
        List<ArticleDto> results;

        pb = PageRequest.of(nPage - 1, Pagination.PAGE_SIZE);
        page = repository.findAllByTexto(strLike, pb);

        if(nPage != 1 && page.getTotalPages() < nPage) // Nro de página fuera de rango
            throw new PageNotFoundException(Constants.EXCEPTION_PAGE_NOT_FOUND, nPage);

        results = page.getContent().stream()
                .map(ArticleConverter::toDto)
                .collect(Collectors.toList());

        return new ResponseResultsDto(
                HttpStatus.OK,
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber() + 1,
                page.getSize(),
                results
        );
    }

    public Article create(Article n) {
        if(repository.findOne(Example.of(n)).isPresent())
            throw new ConstraintViolationException(Constants.EXCEPTION_REGISTER_ALREADY_EXISTS);

        return repository.save(n);
    }

    public ArticleDto create(ArticleDto n) {
        Article temp = ArticleConverter.toEntity(n);

        // Blanqueamos las relaciones y solo registramos los demás atributos.
        temp.setAuthor(null);
        temp.setSource(null);

        return ArticleConverter.toDto(
                this.create(temp)
        );
    }

    public ArticleDto updateById(Long nId, ArticleDto n) {
        Article nExistente;

        nExistente = repository.findById(nId)
                .orElseThrow(
                        () -> new EntityNotFoundException(Constants.EXCEPTION_ENTITY_NOT_FOUND)
                );

        if(!nExistente.getTitle().equals(n.getTitle()))
            nExistente.setTitle(n.getTitle());

        if(!nExistente.getDescription().equals(n.getDescription()))
            nExistente.setDescription(n.getDescription());

        if(!nExistente.getUrl().equals(n.getUrl()))
            nExistente.setUrl(n.getUrl());

        if(!nExistente.getUrlToImage().equals(n.getUrlToImage()))
            nExistente.setUrlToImage(n.getUrlToImage());

        if(nExistente.getPublishedAt() != null && n.getPublishedAt() != null) {
            if (!nExistente.getPublishedAt().isEqual(n.getPublishedAt()))
                nExistente.setPublishedAt(n.getPublishedAt());
        } else
            nExistente.setPublishedAt(n.getPublishedAt());

        if(!nExistente.getContent().equals(n.getContent()))
            nExistente.setContent(n.getContent());

        return ArticleConverter.toDto(repository.save(nExistente));
    }

    public boolean deleteById(Long nId) {
        Article nExistente;

        nExistente = repository.findById(nId)
                .orElseThrow(
                        () -> new EntityNotFoundException(Constants.EXCEPTION_ENTITY_NOT_FOUND)
                );

        if(nExistente.getAuthor() != null)
            nExistente.getAuthor().removeArticle(nExistente);

        if(nExistente.getSource() != null)
            nExistente.getSource().removeArticle(nExistente);

        repository.deleteById(nId);
        return !repository.existsById(nId);
    }

    public ArticleDto setAuthor(Long nIdArticle, Long nIdAuthor) {
        Article art;
        Author aut;

        art = repository.findById(nIdArticle)
                .orElseThrow(
                        () -> new EntityNotFoundException(Constants.EXCEPTION_ENTITY_NOT_FOUND)
                );

        aut = repositoryAuthor.findById(nIdAuthor)
                .orElseThrow(
                        () -> new EntityNotFoundException(Constants.EXCEPTION_ENTITY_NOT_FOUND)
                );

        art.setAuthor(aut);

        return ArticleConverter.toDto(repository.save(art));
    }

    public ArticleDto setSource(Long nIdArticle, Long nIdSource) {
        Article art;
        Source src;

        art = repository.findById(nIdArticle)
                .orElseThrow(
                        () -> new EntityNotFoundException(Constants.EXCEPTION_ENTITY_NOT_FOUND)
                );

        src = repositorySource.findById(nIdSource)
                .orElseThrow(
                        () -> new EntityNotFoundException(Constants.EXCEPTION_ENTITY_NOT_FOUND)
                );

        art.setSource(src);

        return ArticleConverter.toDto(repository.save(art));
    }
}