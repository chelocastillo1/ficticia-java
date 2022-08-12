package com.ficticiajava.main.service;

import com.ficticiajava.main.converter.AuthorConverter;
import com.ficticiajava.main.dto.AuthorDto;
import com.ficticiajava.main.dto.ResponseResultsDto;
import com.ficticiajava.main.entity.Article;
import com.ficticiajava.main.entity.Author;
import com.ficticiajava.main.exception.ConstraintViolationException;
import com.ficticiajava.main.exception.PageNotFoundException;
import com.ficticiajava.main.global.Constants;
import com.ficticiajava.main.global.Pagination;
import com.ficticiajava.main.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    private final AuthorRepository repository;

    @Autowired
    public AuthorService(AuthorRepository repository) {
        this.repository = repository;
    }

    public ResponseResultsDto findAll(int nPage) {
        Pageable pb;
        Page<Author> page;
        List<AuthorDto> results;

        pb = PageRequest.of(nPage - 1, Pagination.PAGE_SIZE);
        page = repository.findAll(pb);

        if(nPage != 1 && page.getTotalPages() < nPage) // Nro de página fuera de rango
            throw new PageNotFoundException(Constants.EXCEPTION_PAGE_NOT_FOUND, nPage);

        results = page.getContent().stream()
                .map(AuthorConverter::toDto)
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

    public ResponseResultsDto findAllByFullName(String strFullName, int nPage) {
        Pageable pb;
        Page<Author > page;
        List<AuthorDto> results;

        pb = PageRequest.of(nPage - 1, Pagination.PAGE_SIZE);
        page = repository.findAllByFullNameContainsIgnoreCase(strFullName, pb);

        if(nPage != 1 && page.getTotalPages() < nPage) // Nro de página fuera de rango
            throw new PageNotFoundException(Constants.EXCEPTION_PAGE_NOT_FOUND, nPage);

        results = page.getContent().stream()
                .map(AuthorConverter::toDto)
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

    public ResponseResultsDto findAllByCreatedAtAfter(LocalDate dCreatedAtAfter, int nPage) {
        Pageable pb;
        Page<Author> page;
        List<AuthorDto> results;

        pb = PageRequest.of(nPage - 1, Pagination.PAGE_SIZE);
        page = repository.findAllByCreatedAtAfter(dCreatedAtAfter, pb);

        if(nPage != 1 && page.getTotalPages() < nPage) // Nro de página fuera de rango
            throw new PageNotFoundException(Constants.EXCEPTION_PAGE_NOT_FOUND, nPage);

        results = page.getContent().stream()
                .map(AuthorConverter::toDto)
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

    public AuthorDto findById(Long nId) {
        return AuthorConverter.toDto(
                repository.findById(nId)
                        .orElseThrow(
                                () -> new EntityNotFoundException(Constants.EXCEPTION_ENTITY_NOT_FOUND)
                        )
        );
    }

    public Author create(Author n) {
        if(!repository.findByFullName(n.getFullName()).isEmpty())
            throw new ConstraintViolationException(Constants.EXCEPTION_REGISTER_ALREADY_EXISTS);

        return repository.save(n);
    }

    public AuthorDto create(AuthorDto n) {
        return AuthorConverter.toDto(
                this.create(AuthorConverter.toEntity(n))
        );
    }

    public AuthorDto updateById(Long nId, AuthorDto n) {
        Author nExistente;
        Optional<Author> temp;

        nExistente = repository
                .findById(nId)
                .orElseThrow(
                        () -> new EntityNotFoundException(Constants.EXCEPTION_ENTITY_NOT_FOUND)
                );

        temp = repository.findByFullName(n.getFullName());

        if(temp.isPresent())
            if(!nExistente.getId().equals(temp.get().getId()))
                throw new ConstraintViolationException(Constants.EXCEPTION_REGISTER_ALREADY_EXISTS);

        if(!nExistente.getFirstName().equals(n.getFirstName()))
            nExistente.setFirstName(n.getFirstName());

        if(!nExistente.getLastName().equals(n.getLastName()))
            nExistente.setLastName(n.getLastName());

        return AuthorConverter.toDto(repository.save(nExistente));
    }

    public boolean deleteById(Long nId) {
        Author nExistente;

        if(repository.countByArticles_AuthorId(nId) > 0) // ¿Este SOURCE tiene referencias a uno o más ARTICLE?
            throw new ConstraintViolationException(Constants.EXCEPTION_CONSTRAINT_VIOLATION);

        nExistente = repository
                .findById(nId)
                .orElseThrow(
                        () -> new EntityNotFoundException(Constants.EXCEPTION_ENTITY_NOT_FOUND)
                );

        for(Article a : nExistente.getArticles())
            a.setAuthor(null);

        repository.deleteById(nId);

        return repository.existsById(nId);
    }
}