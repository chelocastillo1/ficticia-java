package com.ficticiajava.main.runner;

import com.ficticiajava.main.entity.Author;
import com.ficticiajava.main.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Order(2)
public class AuthorRunner implements CommandLineRunner {

    private final AuthorRepository rAuthor;

    @Autowired
    public AuthorRunner(AuthorRepository rAuthor) {
        this.rAuthor = rAuthor;
    }

    @Override
    public void run(String[] args) throws Exception {
        Author[] lista = {
                new Author("Marcelo", "Castillo", LocalDate.of(1985, 8, 25)),
                new Author("Gonzalo", "Castillo", LocalDate.of(1992, 12, 10)),
                new Author("Hugo", "Castillo", LocalDate.of(1958, 1, 20)),
                new Author("Esteban", "Quito", LocalDate.of(1963, 2, 17)),
                new Author("Armando", "Pared", LocalDate.of(1963, 3, 22)),
                new Author("José", "Gonzalez", LocalDate.of(1963, 6, 14)),
                new Author("Juan", "Perez", LocalDate.of(1963, 2, 8)),
                new Author("Melchor", "Reyes", LocalDate.of(1980, 2, 22)),
                new Author("Gaspar", "Reyes", LocalDate.of(1990, 2, 3)),
                new Author("Baltasar", "Reyes", LocalDate.of(1970, 2, 17)),
                new Author("Pepe", "Argento", LocalDate.of(1970, 12, 12)),
                new Author("Mony", "Argento", LocalDate.of(1971, 1, 5)),
                new Author("Coqui", "Argento", LocalDate.of(1972, 5, 30)),
                new Author("Paola", "Argento", LocalDate.of(1973, 7, 31)),
        };

        for(Author n : lista)
            rAuthor.save(n);
    }
}