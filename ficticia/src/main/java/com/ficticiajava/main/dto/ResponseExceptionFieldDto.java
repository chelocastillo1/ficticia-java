package com.ficticiajava.main.dto;

public final class ResponseExceptionFieldDto {

    private final String code;
    private final String field;
    private final Object rejectedValue;
    private final String message;

    public ResponseExceptionFieldDto(String code, String field, Object rejectedValue, String message) {
        this.code = code;
        this.field = field;
        this.rejectedValue = rejectedValue;
        this.message = message;
    }

    /**
     * Representa el código de error, si lo hay.
     **/
    public String getCode() {
        return code;
    }

    /**
     * Hace referencia al campo que provoca la excepción.
     **/
    public String getField() {
        return field;
    }

    /**
     * Muestra el valor que provocó la excepción.
     **/
    public Object getRejectedValue() {
        return rejectedValue;
    }

    /**
     * Devuelve un string que detalla el tipo de error interceptado.
     **/
    public String getMessage() {
        return message;
    }
}