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
                new Source("La Nación", "contenido de la nación", LocalDate.of(203, 1, 11)),
                new Source("Clarín", "contenido de clarín", LocalDate.of(1988, 12, 22)),
                new Source("Página 12", "contenido de página 12", LocalDate.of(1991, 8, 23)),
                new Source("Noticias", "contenido de noticias", LocalDate.of(1973, 7, 24)),
                new Source("Crónica", "contenido crónica", LocalDate.of(1951, 2, 28)),
                new Source("Infobae", "contenido de infobae", LocalDate.of(1970, 6, 26)),
                new Source("CNN En Español", "contenido de cnn en español", LocalDate.of(1944, 12, 21))
        };

        for(Source n : lista)
            repository.save(n);
    }
}