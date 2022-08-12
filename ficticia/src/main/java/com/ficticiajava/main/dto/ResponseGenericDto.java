package com.ficticiajava.main.dto;

import org.springframework.http.HttpStatus;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
abstract class ResponseGenericDto {
    private final HttpStatus status;

    public ResponseGenericDto(HttpStatus status) {
        this.status = status;
    }

    /**
     * Devuelve el estado HTTP.
     **/
    public HttpStatus getStatus() {
        return status;
    }
}
