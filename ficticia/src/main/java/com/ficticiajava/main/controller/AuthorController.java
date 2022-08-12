package com.ficticiajava.main.controller;

import com.ficticiajava.main.dto.AuthorDto;
import com.ficticiajava.main.dto.ResponseExceptionFieldDto;
import com.ficticiajava.main.dto.ResponseExceptionValidationDto;
import com.ficticiajava.main.global.Constants;
import com.ficticiajava.main.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Set;

@Validated
@RestController
@RequestMapping(value = Constants.ENDPOINT_AUTHOR)
public class AuthorController {

    private final AuthorService service;

    @Autowired
    public AuthorController(AuthorService service) {
        this.service = service;
    }

    @GetMapping() // Sin parámetros, excepto page.
    public ResponseEntity<?> findAll(
            @Valid @Positive @RequestParam(name = "page", required = false, defaultValue = "1") int nPage
    ) {
        return new ResponseEntity<>(
                service.findAll(nPage),
                null,
                HttpStatus.OK
        );
    }

    @GetMapping(params = {"id"})// Ejemplo: http://.../author?id={id}
    public ResponseEntity<?> findById(
            @Valid @Positive @RequestParam(name = "id", defaultValue = "0") Long nId
    ) {
        return new ResponseEntity<>(
                service.findById(nId),
                null,
                HttpStatus.OK
        );
    }

    @GetMapping(params = {"createdAtAfter"}) // Ejemplo: http://.../author?createdAtAfter={createdAtAfter}
    public ResponseEntity<?> findAllByCreatedAtAfter(
            @Valid @NotBlank @RequestParam(name = "createdAtAfter", defaultValue = "") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String dCreatedAtAfter,
            @Valid @Positive @RequestParam(name = "page", required = false, defaultValue = "1") int nPage
    ) throws DateTimeParseException {
        LocalDate tmp;

        tmp = LocalDate.parse(dCreatedAtAfter); // Si no lo puede "parsear" a una fecha válida
                                                // desencadena la excepción DateTimeParseException

        return new ResponseEntity<>(
                service.findAllByCreatedAtAfter(tmp, nPage),
                null,
                HttpStatus.OK
        );
    }

    @GetMapping(params = {"fullName"})// Ejemplo: http://.../author?fullName={fullname}
    public ResponseEntity<?> findAllByFullName(
            @Valid @NotBlank @RequestParam(name = "fullName", defaultValue = "") String strFullName,
            @Valid @Positive @RequestParam(name = "page", required = false, defaultValue = "1") int nPage
    ) {
        return new ResponseEntity<>(
                service.findAllByFullName(strFullName, nPage),
                null,
                HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<?> create(
            @Valid @RequestBody AuthorDto n
    ) {
        return new ResponseEntity<>(
                service.create(n),
                null,
                HttpStatus.CREATED
            );
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateById(
            @Valid @Positive @PathVariable("id") Long nId,
            @Valid @RequestBody AuthorDto n
    ) {
        return new ResponseEntity<>(
                service.updateById(nId, n),
                null,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(
            @Valid @Positive @PathVariable("id") Long nId
    ) {
        return new ResponseEntity<>(
                service.deleteById(nId) ? "deleted" : "not deleted",
                null,
                HttpStatus.NO_CONTENT
        );
    }
}