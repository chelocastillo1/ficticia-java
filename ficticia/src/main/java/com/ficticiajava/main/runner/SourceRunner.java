package com.ficticiajava.main.runner;

import com.ficticiajava.main.entity.Source;
import com.ficticiajava.main.repository.SourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(1)
public class SourceRunner implements CommandLineRunner {

    private final SourceRepository repository;

    @Autowired
    public SourceRunner(SourceRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String[] args) throws Exception {
        Source[] lista = {
                new Source("La Nación", "contenido", LocalDate.of(2000, 1, 1)),
                new Source("Clarín", "contenido", LocalDate.of(1992, 2, 2)),
                new Source("Página 12", "contenido", LocalDate.of(1990, 3, 3)),
                new Source("Noticias", "contenido", LocalDate.of(1970, 4, 4))
        };

        for(Source n : lista)
            repository.save(n);
    }
}