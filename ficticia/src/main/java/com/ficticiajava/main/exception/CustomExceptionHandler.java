package com.ficticiajava.main.exception;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.ficticiajava.main.dto.ResponseExceptionDto;
import com.ficticiajava.main.dto.ResponseExceptionFieldDto;
import com.ficticiajava.main.dto.ResponseExceptionValidationDto;
import com.ficticiajava.main.global.Constants;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Set;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    public CustomExceptionHandler() {
        super();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public final @NotNull ResponseEntity<Object> handleConstraintViolation(
            @NotNull ConstraintViolationException ex
    ) {
        ResponseExceptionValidationDto res = new ResponseExceptionValidationDto(
                HttpStatus.BAD_REQUEST,
                Constants.EXCEPTION_VALIDATION,
                LocalDateTime.now(),
                ex.getConstraintViolations().size(),
                ex.getConstraintViolations().stream()
                        .map(v -> new ResponseExceptionFieldDto(
                                v.getMessageTemplate(),
                                v.getPropertyPath().toString(),
                                v.getInvalidValue(),
                                v.getMessage())
                        )
                        .collect(Collectors.toSet())
        );

        return new ResponseEntity<>(
                res,
                null,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(com.ficticiajava.main.exception.ConstraintViolationException.class)
    public final @NotNull ResponseEntity<Object> handleConstraintViolationCustomized(
            @NotNull com.ficticiajava.main.exception.ConstraintViolationException ex
    ) {
        return new ResponseEntity<>(
                new ResponseExceptionDto(
                        HttpStatus.CONFLICT,
                        ex.getMessage(),
                        LocalDateTime.now()
                ),
                null,
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final @NotNull ResponseEntity<Object> handleDataIntegrityViolation(
            @NotNull DataIntegrityViolationException ex
    ) {
        return new ResponseEntity<>(
                new ResponseExceptionDto(
                        HttpStatus.BAD_REQUEST,
                        ex.getMostSpecificCause().getMessage(),
                        LocalDateTime.now()
                ),
                null,
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DateTimeParseException.class)
    public final @NotNull ResponseEntity<Object> handleDateTimeParse(
            @NotNull DateTimeParseException ex
    ) {
        ResponseExceptionValidationDto res = new ResponseExceptionValidationDto(
                HttpStatus.BAD_REQUEST,
                Constants.EXCEPTION_VALIDATION,
                LocalDateTime.now(),
                1,
                Set.of(
                        new ResponseExceptionFieldDto(
                                String.format("%s", ex.getErrorIndex()),
                                null,
                                ex.getParsedString(),
                                Constants.EXCEPTION_INVALID_FORMAT
                        )
                )
        );

        return new ResponseEntity<>(
                res,
                null,
                HttpStatus.BAD_REQUEST
        );
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public final @NotNull ResponseEntity<Object> handleEntityNotFound(
            @NotNull EntityNotFoundException ex
    ) {
        return new ResponseEntity<>(
                new ResponseExceptionDto(
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        LocalDateTime.now()
                ),
                null,
                HttpStatus.NOT_FOUND
        );
    }

    @Override
    public @NotNull ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {
        Object nReturn = null; // ResponseExceptionValidationDto nReturn = null;
        boolean bIntercepted = false;

        // Verificar si fue por formato no válido.
        if(!bIntercepted && ex.getCause() instanceof InvalidFormatException) {
            nReturn = new ResponseExceptionValidationDto(
                    HttpStatus.BAD_REQUEST,
                    Constants.EXCEPTION_VALIDATION,
                    LocalDateTime.now(),
                    1,
                    Set.of(
                            new ResponseExceptionFieldDto(
                                    null,
                                    null,
                                    ex.getRootCause().getMessage(),
                                    Constants.EXCEPTION_INVALID_FORMAT
                            )
                    )
            );

            bIntercepted = true;
        }

        // Verificar si fue por formato no válido de tipo JSON.
        if(!bIntercepted && ex.getCause() instanceof JsonParseException) {
//            JsonParseException exTemporal = new JsonParseException(null, Constants.EXCEPTION_VALIDATION_JSON_FORMAT, ex);
//            nReturn = new ResponseExceptionValidationDto(
//                    HttpStatus.BAD_REQUEST,
//                    Constants.EXCEPTION_VALIDATION,
//                    LocalDateTime.now(),
//                    1,
//                    Set.of(
//                            new ResponseExceptionFieldDto(
//                                    null,
//                                    null,
//                                    ex.getRootCause().getMessage(),
//                                    Constants.EXCEPTION_INVALID_FORMAT
//                            )
//                    )
//            );

            nReturn = new ResponseExceptionDto(
                            HttpStatus.BAD_REQUEST,
                            Constants.EXCEPTION_VALIDATION_JSON_FORMAT,
                            LocalDateTime.now()
                    );

//            JsonMappingException.Reference ref = ((JsonMappingException) ex.getCause()).getPath().get(0);
//
//            nReturn = new ResponseExceptionValidationDto(
//                    status,
//                    Constants.EXCEPTION_INVALID_FORMAT,
//                    LocalDateTime.now(),
//                    1,
//                    Set.of(
//                            new ResponseExceptionFieldDto(
//                                    ex.getMostSpecificCause().getClass().getName(),
//                                    ref.getFieldName(),
//                                    ex.getMostSpecificCause().getMessage().substring(ex.getMostSpecificCause().getMessage().lastIndexOf(':')+1).trim(),
//                                    ex.getMostSpecificCause().getMessage()
//                            )
//                    )
//            );

            bIntercepted = true;
        }

        return new ResponseEntity<>(
                nReturn,
                headers,
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    public @NotNull ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {
        return new ResponseEntity<>(
                new ResponseExceptionDto(
                        HttpStatus.METHOD_NOT_ALLOWED,
                        ex.getMessage(),
                        LocalDateTime.now()
                ),
                headers,
                HttpStatus.METHOD_NOT_ALLOWED
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public @NotNull ResponseEntity<Object> handleIllegalArgument(
            IllegalArgumentException ex
    ) {
        return new ResponseEntity<>(
                new ResponseExceptionDto(
                        HttpStatus.BAD_REQUEST,
                        ex.getMessage(),
                        LocalDateTime.now()
                ),
                null,
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    public @NotNull ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {
        ResponseExceptionValidationDto res = new ResponseExceptionValidationDto(
                status,
                Constants.EXCEPTION_VALIDATION,
                LocalDateTime.now(),
                ex.getBindingResult().getErrorCount(),
                ex.getBindingResult().getFieldErrors().stream()
                        .map(f -> new ResponseExceptionFieldDto(
                                f.getCode(),
                                f.getField(),
                                f.getRejectedValue(),
                                f.getDefaultMessage())
                        )
                        .collect(Collectors.toSet())
        );

        return new ResponseEntity<>(
                res,
                headers,
                status
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final @NotNull ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            @NotNull MethodArgumentTypeMismatchException ex
    ) {
        ResponseExceptionValidationDto res = new ResponseExceptionValidationDto(
                HttpStatus.BAD_REQUEST,
                Constants.EXCEPTION_VALIDATION,
                LocalDateTime.now(),
                1,
                Set.of(
                        new ResponseExceptionFieldDto(
                                null,
                                ex.getName(),
                                ex.getValue(),
                                Constants.EXCEPTION_INVALID_FORMAT
                        )
                )
        );

        return new ResponseEntity<>(
                res,
                null,
                HttpStatus.BAD_REQUEST
        );
    }


    @ExceptionHandler(MismatchedInputException.class)
    public final @NotNull ResponseEntity<Object> handleMismatchedInput(
            @NotNull MismatchedInputException ex
    ) {
        return new ResponseEntity<>(
                new ResponseExceptionDto(
                        HttpStatus.BAD_REQUEST,
                        String.format("MismatchedInputException: %s", ex.getMessage()),
                        LocalDateTime.now()
                ),
                null,
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    public final @NotNull ResponseEntity<Object> handleMissingPathVariable(
            @NotNull MissingPathVariableException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {
        return new ResponseEntity<>(
                new ResponseExceptionDto(
                        HttpStatus.BAD_REQUEST,
                        String.format("Parámetro no válido: %s", ex.getVariableName()),
                        LocalDateTime.now()
                ),
                null,
                HttpStatus.BAD_REQUEST
        );
    }

    //@ExceptionHandler(MissingServletRequestParameterException.class)
    @Override
    public final @NotNull ResponseEntity<Object> handleMissingServletRequestParameter(
            @NotNull MissingServletRequestParameterException ex,
            @NotNull HttpHeaders headers,
            @NotNull HttpStatus status,
            @NotNull WebRequest request
    ) {
        ResponseExceptionValidationDto nReturn = new ResponseExceptionValidationDto(
                HttpStatus.BAD_REQUEST,
                Constants.EXCEPTION_VALIDATION,
                LocalDateTime.now(),
                1,
                Set.of(
                        new ResponseExceptionFieldDto(
                                null,
                                ex.getParameterName(),
                                Constants.UNKNOWN,
                                ex.getMessage()
                        )
                )
        );

        return new ResponseEntity<>(
                nReturn,
                headers,
                status
        );
    }

    @ExceptionHandler(NullPointerException.class)
    public final @NotNull ResponseEntity<Object> handleNullPointer(
            @NotNull NullPointerException ex
    ) {
        return new ResponseEntity<>(
                new ResponseExceptionDto(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        ex.getMessage(),
                        LocalDateTime.now()
                ),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(com.ficticiajava.main.exception.PageNotFoundException.class)
    public final @NotNull ResponseEntity<Object> handlePageNotFound(
            @NotNull com.ficticiajava.main.exception.PageNotFoundException ex
    ) {
        return new ResponseEntity<>(
                new ResponseExceptionDto(
                        HttpStatus.NOT_FOUND,
                        ex.getMessage(),
                        LocalDateTime.now()
                ),
                null,
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public final @NotNull ResponseEntity<Object> handleUnexpectedType(
            @NotNull UnexpectedTypeException ex
    ) {
        ResponseExceptionValidationDto res = new ResponseExceptionValidationDto(
                HttpStatus.BAD_REQUEST,
                "Excepción inesperada",
                LocalDateTime.now(),
                1,
                Set.of(
                        new ResponseExceptionFieldDto(
                                null,
                                ex.getMessage(),
                                ex.getLocalizedMessage(),
                                Constants.EXCEPTION_INVALID_FORMAT
                        )
                )
        );

        return new ResponseEntity<>(
                res,
                null,
                HttpStatus.BAD_REQUEST
        );
    }

//    @ExceptionHandler(ConversionFailedException.class)
//    public final @NotNull ResponseEntity<Object> handleConversionFailed(
//            @NotNull ConversionFailedException ex,
//            @NotNull WebRequest request
//    ) {
//        Set<ResponseExceptionFieldDto> lst = new HashSet<>();
//        JsonMappingException.Reference ref = ((JsonMappingException) ex.getCause()).getPath().get(0);
//
//        lst.add(
//                new ResponseExceptionFieldDto(
//                        ex.getMostSpecificCause().getClass().getName(),
//                        ref.getFieldName(),
//                        ex.getMostSpecificCause().getMessage().substring(ex.getMostSpecificCause().getMessage().lastIndexOf(':')+1).trim(),
//                        ex.getMostSpecificCause().getMessage()
//                )
//        );
//
//        ResponseExceptionValidationDto res = new ResponseExceptionValidationDto(
//                HttpStatus.BAD_REQUEST,
//                "Query no válida",
//                LocalDateTime.now(),
//                1,
//                lst
//        );
//
//        return new ResponseEntity<>(
//                res,
//                null,
//                HttpStatus.BAD_REQUEST
//        );
//    }
//
//    @ExceptionHandler(IllegalArgumentException.class)
//    public @NotNull ResponseEntity<Object> handleIllegalArgument(
//            IllegalArgumentException ex
//    ) {
//        return new ResponseEntity<>(
//                new ResponseExceptionDto(
//                        HttpStatus.CONFLICT,
//                        ex.getMessage(),
//                        LocalDateTime.now()
//                ),
//                null,
//                HttpStatus.CONFLICT
//        );
//    }
//
//    @ExceptionHandler(PropertyValueException.class)
//    public final @NotNull ResponseEntity<Object> handlePropertyValue(
//            @NotNull PropertyValueException ex,
//            @NotNull WebRequest request
//    ) {
//        Set<ResponseExceptionFieldDto> lst = new HashSet<>();
//
//        lst.add(
//                new ResponseExceptionFieldDto(
//                        ex.getCause().getLocalizedMessage(),
//                        ex.getEntityName(),
//                        "N/D",
//                        ex.getMessage()
//                )
//        );
//
//        ResponseExceptionValidationDto res = new ResponseExceptionValidationDto(
//                HttpStatus.BAD_REQUEST,
//                "Invalid format",
//                LocalDateTime.now(),
//                1,
//                lst
//        );
//
//        return new ResponseEntity<>(
//                res,
//                HttpStatus.BAD_REQUEST
//        );
//    }
}