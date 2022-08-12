package com.ficticiajava.main.converter;

import com.ficticiajava.main.dto.AuthorDto;
import com.ficticiajava.main.entity.Author;
import org.springframework.stereotype.Component;

@Component
public final class AuthorConverter {

    /**
     * Permite mapear de objeto DTO a ENTITY.
     * @param dto Objeto DTO a convertir.
     * @return Devuelve el objeto ENTITY convertido.
     **/
    public static Author toEntity(AuthorDto dto) {
        return new Author(
                dto.getId(),
                dto.getFirstName(),
                dto.getLastName(),
                dto.getCreatedAt()
        );
    }

    /**
     * Permite mapear de objeto ENTITY a DTO.
     * @param e Objeto ENTITY a convertir.
     * @return Devuelve el objeto DTO convertido.
     **/
    public static AuthorDto toDto(Author e) {
        return new AuthorDto(
                e.getId(),
                e.getFirstName(),
                e.getLastName(),
                e.getCreatedAt()
        );
    }
}