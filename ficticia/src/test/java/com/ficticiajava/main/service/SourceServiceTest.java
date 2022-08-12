package com.ficticiajava.main.service;

import com.ficticiajava.main.converter.SourceConverter;
import com.ficticiajava.main.dto.SourceDto;
import com.ficticiajava.main.entity.Source;
import com.ficticiajava.main.exception.ConstraintViolationException;
import com.ficticiajava.main.repository.SourceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SourceServiceTest {

    @Mock
    private SourceRepository repository;

    @InjectMocks
    private SourceService service;

    /**
     * Given: El parámetro nId es cero (0).
     * When: Se invoca a findById() para obtener la entidad referenciada por el ID.
     * Then: Produce una excepción de tipo EntityNotFoundException.
     **/
    @Test
    void findById_entityNotFoundException() {
        Long nId = 0L;

        when(repository.findById(nId))
                .thenReturn(Optional.empty());

        Exception ex = assertThrows(
                EntityNotFoundException.class,
                () -> service.findById(nId)
        );

        assertNotNull(ex);
        assertEquals(EntityNotFoundException.class, ex.getClass());
    }

    /**
     * Given: Todos los datos correctos.
     * When: Intenta procesar los datos y crear el nuevo registro.
     * Then: Registra exitosamente el DTO en la base de datos.
     **/
    @Test
    void createSource_happyRoad() {
        Source whenSourceOk = new Source(
                "Diario Pinedense",
                "Noticias en general",
                LocalDate.of(2000, 12 , 12)
                );

        when(repository.save(whenSourceOk)).
                thenReturn(new Source(
                        30L,
                        "Diario Pinedense",
                        "Noticias en general",
                        LocalDate.of(2000, 12 , 12)
                ));
        when(repository.findByName(whenSourceOk.getName()))
                .thenReturn(Optional.empty());

        SourceDto dto = service.create(SourceConverter.toDto(whenSourceOk));

        assertNotNull(dto);
        assertEquals(whenSourceOk, SourceConverter.toEntity(dto));
        assertNotEquals(null, dto.getId());
        assertEquals("diario-pinedense", dto.getCode());
    }

    /**
     * Given: Un Entity que contiene un Name en particular.
     * When: Verifica si el Name del Entity ya existe en la tabla.
     * Then: Produce una excepción del tipo ConstraintViolationException debido a que ya existe el Name.
     **/
    @Test
    void createSource_entityAlreadyExists() {
        String whenNameChecking = "Pinedo News";

        Source whenSourceOk = new Source(
                whenNameChecking,
                "Noticias en general",
                LocalDate.of(2000, 12 , 12)
        );

        when(repository.findByName(whenNameChecking))
                .thenReturn(Optional.of(whenSourceOk));

        Exception ex = assertThrows(
                ConstraintViolationException.class,
                () -> service.create(SourceConverter.toDto(whenSourceOk))
        );

        assertNotNull(ex);
        assertEquals(ConstraintViolationException.class, ex.getClass());
    }
}