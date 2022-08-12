package com.ficticiajava.main.converter;

import com.ficticiajava.main.dto.SourceDto;
import com.ficticiajava.main.entity.Source;
import org.springframework.stereotype.Component;

@Component
public final class SourceConverter {
    /**
     * Permite mapear de objeto DTO a ENTITY.
     * @param dto Objeto DTO a convertir.
     * @return Devuelve el objeto ENTITY convertido.
     **/
    public static Source toEntity(SourceDto dto) {
        return new Source(
                dto.getId(),
                dto.getName(),
                dto.getContenido(),
                dto.getCreatedAt()
        );
    }
    /**
     * Permite mapear de objeto ENTITY a DTO.
     * @param e Objeto ENTITY a convertir.
     * @return Devuelve el objeto DTO convertido.
     **/
    public static SourceDto toDto(Source e) {
        return new SourceDto(
                e.getId(),
                e.getName(),
                e.getContenido(),
                e.getCreatedAt()
        );
    }
}