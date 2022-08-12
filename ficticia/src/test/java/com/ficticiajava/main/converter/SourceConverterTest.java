package com.ficticiajava.main.converter;

import com.ficticiajava.main.dto.SourceDto;
import com.ficticiajava.main.entity.Source;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class SourceConverterTest {

    @Test
    void toEntity() {
        Object[] datos = {1L,
                "Noticias Chaco",
                "Periodismo en general",
                LocalDate.of(1950, 05, 25)
        };
        SourceDto d;

        Source expected = new Source(
                (Long)datos[0],
                (String)datos[1],
                (String)datos[2],
                (LocalDate) datos[3]
        );

        d = new SourceDto(
                (Long)datos[0],
                (String)datos[1],
                (String)datos[2],
                (LocalDate) datos[3]
        );

        assertEquals(expected, SourceConverter.toEntity(d));
    }

    @Test
    void toDto() {
        Object[] datos = {1L,
                "Noticias Chaco",
                "Periodismo en general",
                LocalDate.of(1950, 05, 25)
        };
        Source e;

        SourceDto expected = new SourceDto(
                (Long)datos[0],
                (String)datos[1],
                (String)datos[2],
                (LocalDate) datos[3]
        );

        e = new Source(
                (Long)datos[0],
                (String)datos[1],
                (String)datos[2],
                (LocalDate) datos[3]
        );

        assertEquals(expected, SourceConverter.toDto(e));
    }
}