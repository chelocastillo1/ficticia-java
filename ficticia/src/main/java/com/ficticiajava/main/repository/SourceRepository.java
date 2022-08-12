package com.ficticiajava.main.repository;

import com.ficticiajava.main.entity.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long>, PagingAndSortingRepository<Source, Long> {

    Long countByArticles_SourceId(Long id);

    Optional<Source> findByName(String name);

    Page<Source> findAllByNameContainsIgnoreCase(String name, Pageable pb);
}