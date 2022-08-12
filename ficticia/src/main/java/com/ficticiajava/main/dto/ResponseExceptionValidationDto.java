package com.ficticiajava.main.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Set;

public final class ResponseExceptionValidationDto extends ResponseExceptionDto {

    private final int totalErrors;
    private final Set<ResponseExceptionFieldDto> errors;

    public ResponseExceptionValidationDto(HttpStatus status, String message, LocalDateTime time, int totalErrors, Set<ResponseExceptionFieldDto> errors) {
        super(status, message, time);
        this.totalErrors = totalErrors;
        this.errors = errors;
    }

    /**
     * Devuelve un valor entero que representa la cantidad de errores provocados.
     **/
    public int getTotalErrors() {
        return totalErrors;
    }

    /**
     * Conjunto (Set) de los errores interceptados.
     **/
    public Set<ResponseExceptionFieldDto> getErrors() {
        return errors;
    }
}