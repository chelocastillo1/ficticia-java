package com.ficticiajava.main.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Validated
public final class SourceDto extends GenericDto {

    @NotBlank
    private final String name;

    //@NotBlank
    private final String code;

    @NotBlank
    private final String contenido;

    //@JsonFormat(pattern="yyyy-MM-dd")
    @PastOrPresent
    private final LocalDate createdAt;

    public SourceDto(Long id, String name, String contenido, LocalDate createdAt) {
        super(id);
        this.name = name;
        this.code = name != null ? this.getNameToCode(name) : null;
        this.contenido = contenido;
        this.createdAt = createdAt;
    }

    /**
     * Permite formatear un texto para poder utilizarlo como el código (code) para el Source.
     * @param strName Cadena de texto a formatear
     * @return Devuelve una cadena formateada en minúsculas con guiones en vez de espacios.
     * */
    private String getNameToCode(String strName) {
        return String.format("%s", strName.toLowerCase().replace(' ', '-'));
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getContenido() {
        return contenido;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SourceDto)) return false;

        SourceDto sourceDto = (SourceDto) o;

        if (!name.equals(sourceDto.name)) return false;
        if (!code.equals(sourceDto.code)) return false;
        if (!contenido.equals(sourceDto.contenido)) return false;
        return createdAt.equals(sourceDto.createdAt);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + contenido.hashCode();
        result = 31 * result + createdAt.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SourceDto{" +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", contenido='" + contenido + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}