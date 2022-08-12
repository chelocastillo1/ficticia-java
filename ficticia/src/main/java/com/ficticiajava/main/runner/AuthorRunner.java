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
                new Author("Liliana", "Lemaire", LocalDate.of(1963, 2, 8))
        };

//        Author[] lista = {
//                new Author("Marcelo", "Castillo", "1985-08-25"),
//                new Author("Gonzalo", "Castillo", "1992-12-10"),
//                new Author("Hugo", "Castillo", "1958-01-20"),
//                new Author("Liliana", "Lemaire", "1963-02-08")
//        };

        for(Author n : lista)
            rAuthor.save(n);
    }
}