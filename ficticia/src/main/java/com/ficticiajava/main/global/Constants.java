package com.ficticiajava.main.global;

/**
 * Constantes globales
 **/
public final class Constants {

    public static final String UNKNOWN = "unknown";

    private static final String ENDPOINT_BASE = "/api/v1/";
    public static final String ENDPOINT_ARTICLE = ENDPOINT_BASE + "article";
    public static final String ENDPOINT_AUTHOR = ENDPOINT_BASE + "author";
    public static final String ENDPOINT_CATEGORY = ENDPOINT_BASE + "category";
    //public static final String ENDPOINT_HELPER = ENDPOINT_BASE + "help";
    public static final String ENDPOINT_SOURCE = ENDPOINT_BASE + "source";

    /**
     * Constantes para mensajes de manejo de Excepciones.
     **/
    public static final String EXCEPTION_VALIDATION = "Excepción de validación";
    public static final String EXCEPTION_VALIDATION_JSON_FORMAT = "Excepción en sintaxis JSON";
    public static final String EXCEPTION_CONSTRAINT_VIOLATION = "Este registro tiene claves foráneas en otras tablas.";
    public static final String EXCEPTION_ENTITY_NOT_FOUND = "El recurso requerido no existe en la base de datos.";
    public static final String EXCEPTION_INVALID_FORMAT = "Formato no válido.";

    public static final String EXCEPTION_PAGE_NOT_FOUND = "No se ha encontrado el número de página requerida.";
    public static final String EXCEPTION_REGISTER_ALREADY_EXISTS = "Ya existe un registro con este nombre y apellido en la tabla.";
}
