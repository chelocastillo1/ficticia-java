package com.ficticiajava.main.repository;

import com.ficticiajava.main.entity.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>, PagingAndSortingRepository<Author, Long> {

    Long countByArticles_AuthorId(Long id);

    Optional<Author> findByFullName(String fullName);

    Page<Author> findAllByFullNameContainsIgnoreCase(String fullName, Pageable pb);

    Page<Author> findAllByCreatedAtAfter(LocalDate createdAt, Pageable pb);
}