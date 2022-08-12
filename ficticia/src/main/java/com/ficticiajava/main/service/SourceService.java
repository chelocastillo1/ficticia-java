package com.ficticiajava.main.service;

import com.ficticiajava.main.converter.SourceConverter;
import com.ficticiajava.main.dto.ResponseResultsDto;
import com.ficticiajava.main.dto.SourceDto;
import com.ficticiajava.main.entity.Article;
import com.ficticiajava.main.entity.Source;
import com.ficticiajava.main.exception.ConstraintViolationException;
import com.ficticiajava.main.exception.PageNotFoundException;
import com.ficticiajava.main.global.Constants;
import com.ficticiajava.main.global.Pagination;
import com.ficticiajava.main.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SourceService {

    private final SourceRepository repository;

    @Autowired
    public SourceService(SourceRepository repository) {
        this.repository = repository;
    }

    /**
     * Permite formatear un texto para poder utilizarlo como el código (code) para el Source.
     * @param str Cadena de texto a formatear
     * @return Devuelve una cadena formateada en minúsculas con guiones en vez de espacios.
     * */
    public static String toStringCoded(String str) {
        return String.format("%s", str.toLowerCase().replace(' ', '-'));
    }

    public ResponseResultsDto findAll(int nPage) {
        Pageable pb;
        Page<Source> page;
        List<SourceDto> results;

        pb = PageRequest.of(nPage - 1, Pagination.PAGE_SIZE);
        page = repository.findAll(pb);

        if(nPage != 1 && page.getTotalPages() < nPage) // Nro de página fuera de rango
            throw new PageNotFoundException(Constants.EXCEPTION_PAGE_NOT_FOUND, nPage);

        results = page.getContent().stream()
                .map(SourceConverter::toDto)
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

    public ResponseResultsDto findAllByName(String strName, int nPage) {
        Pageable pb;
        Page<Source> page;
        List<SourceDto> results;

        pb = PageRequest.of(nPage - 1, Pagination.PAGE_SIZE);
        page = repository.findAllByNameContainsIgnoreCase(strName, pb);

        if(nPage != 1 && page.getTotalPages() < nPage) // Nro de página fuera de rango
            throw new PageNotFoundException(Constants.EXCEPTION_PAGE_NOT_FOUND, nPage);

        results = page.getContent().stream()
                .map(SourceConverter::toDto)
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

    public SourceDto findById(Long nId) {
        return SourceConverter.toDto(
                repository.findById(nId)
                        .orElseThrow(
                                () -> new EntityNotFoundException(Constants.EXCEPTION_ENTITY_NOT_FOUND)
                        )
        );
    }

    public Source create(Source n) {
        if(!repository.findByName(n.getName()).isEmpty())
            throw new ConstraintViolationException(Constants.EXCEPTION_REGISTER_ALREADY_EXISTS);

        return repository.save(n);
    }

    public SourceDto create(SourceDto n) {
        return SourceConverter.toDto(
                this.create(SourceConverter.toEntity(n))
        );
    }

    public SourceDto updateById(Long nId, SourceDto n) {
        Source nExistente;
        Optional<Source> temp;

        nExistente = repository
                .findById(nId)
                .orElseThrow(
                        () -> new EntityNotFoundException(Constants.EXCEPTION_ENTITY_NOT_FOUND)
                );

        temp = repository.findByName(n.getName());

        if(temp.isPresent())
            if(!Objects.equals(nExistente.getId(), temp.get().getId()))
                throw new ConstraintViolationException(Constants.EXCEPTION_REGISTER_ALREADY_EXISTS);

        if(!nExistente.getName().equals(n.getName()))
            nExistente.setName(n.getName());

        if(!nExistente.getCode().equals(n.getCode()))
            nExistente.setCode(n.getCode());

        if(!nExistente.getContenido().equals(n.getContenido()))
            nExistente.setContenido(n.getContenido());

        return SourceConverter.toDto(repository.save(nExistente));
    }

    public boolean deleteById(Long nId) {
        Source nExistente;

        if(repository.countByArticles_SourceId(nId) > 0) // ¿Este SOURCE tiene referencias a uno o más ARTICLE?
            throw new ConstraintViolationException(Constants.EXCEPTION_CONSTRAINT_VIOLATION);

        nExistente = repository
                .findById(nId)
                .orElseThrow(
                        () -> new EntityNotFoundException(Constants.EXCEPTION_ENTITY_NOT_FOUND)
                );

        for(Article a : nExistente.getArticles())
            a.setSource(null);

        repository.deleteById(nId);

        return repository.existsById(nId);
    }
}