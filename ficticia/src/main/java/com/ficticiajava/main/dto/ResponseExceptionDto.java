package com.ficticiajava.main.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Definición de una clase que utilizaremos para devolver un Body Json personalizado
 * cuando ocurra un error en las REQUEST HTTP.
 **/
public class ResponseExceptionDto extends ResponseGenericDto {
    private final int error;
    private final String message;
    private final LocalDateTime dateTime;

    /**
     * @param status Representa el código HttpStatus a mostrar.
     * @param message Un texto que identifique el error.
     * @param dateTime Fecha y hora en la que se produjo el error. */
    public ResponseExceptionDto(HttpStatus status, String message, LocalDateTime dateTime) {
        super(status);
        this.error = status != null ? status.value() : 0;
        this.message = message;
        this.dateTime = dateTime;
    }

    /**
     * Devuelve un entero que representa el código de error HTTPS Status.
     **/
    public int getError() {
        return error;
    }

    /**
     * Retorna una cadena de texto con ínformación relacionada al error provocado.
     **/
    public String getMessage() {
        return message;
    }

    /**
     * Deuvelve un fecha y hora en la que se produjo el error.
     **/
    public LocalDateTime getDateTime() {
        return dateTime;
    }
}