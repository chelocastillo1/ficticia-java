package com.ficticiajava.main.dto;

import org.springframework.http.HttpStatus;

public final class ResponseResultsDto extends ResponseGenericDto {
    private final long totalResults;
    private final int totalPages;
    private final int page;
    private final int pageSize;
    private final Object data;

    public ResponseResultsDto(HttpStatus status, long totalResults, int totalPages, int page, int pageSize, Object data) {
        super(status);
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.page = page;
        this.pageSize = pageSize;
        this.data = data;
    }

    /**
     * Retorna el total de elementos encontrados.
     **/
    public long getTotalResults() {
        return totalResults;
    }

    /**
     * Devuelve el total de páginas calculadas.
     **/
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Devuelve el número de página actual que se está visualizando.
     **/
    public int getPage() {
        return page;
    }

    /**
     * Devuelve un entero que representa la cantidad de elementos mostrados por página.
     **/
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Devuelve un List que contiene los resultados obtenidos de la consulta.
     **/
    public Object getData() {
        return data;
    }
}