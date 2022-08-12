package com.ficticiajava.main.entity;

import javax.persistence.*;

/**
 * Clase genérica/base que se utilizará en las clases de tipo ENTITY.
 **/
@MappedSuperclass
abstract class GenericEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public GenericEntity() {
        // default, requerida siempre
    }

    public GenericEntity(Long nId) {
        setId(nId);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long valor) {
        this.id = valor;
    }
}