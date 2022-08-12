package com.ficticiajava.main.dto;

import javax.persistence.*;

@MappedSuperclass
abstract class GenericDto {

    private final Long id;

    public GenericDto(Long nId) {
        this.id = nId;
    }

    public Long getId() {
        return this.id;
    }

}