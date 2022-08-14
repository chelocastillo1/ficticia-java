package com.ficticiajava.main.global;

/**
 * Constantes globales
 **/
public final class Constants {

    public static final String UNKNOWN = "unknown";

    public static final String ENDPOINT = "";
    public static final String ENDPOINT_API = ENDPOINT + "/api";
    public static final String ENDPOINT_API_V1 = ENDPOINT_API + "/v1";
    public static final String ENDPOINT_ARTICLE = ENDPOINT_API_V1 + "/article";
    public static final String ENDPOINT_AUTHOR = ENDPOINT_API_V1 + "/author";
    public static final String ENDPOINT_CATEGORY = ENDPOINT_API_V1 + "/category";
    //public static final String ENDPOINT_HELPER = ENDPOINT_API_V1 + "/help";
    public static final String ENDPOINT_SOURCE = ENDPOINT_API_V1 + "/source";

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
