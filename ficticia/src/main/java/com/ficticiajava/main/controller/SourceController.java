package com.ficticiajava.main.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ficticiajava.main.dto.SourceDto;
import com.ficticiajava.main.global.Constants;
import com.ficticiajava.main.service.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Validated
@RestController
@RequestMapping(value = Constants.ENDPOINT_SOURCE)
public class SourceController {

    private final SourceService service;

    @Autowired
    public SourceController(SourceService service) {
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

    @GetMapping(params = {"id"}) // Ejemplo: http://.../source?id={id}
    public ResponseEntity<?> findById(
            @Valid @Positive @RequestParam(name = "id", defaultValue = "0") Long nId
    ) {
        return new ResponseEntity<>(
                service.findById(nId),
                null,
                HttpStatus.OK
        );
    }

    @GetMapping(params = {"name"}) // Ejemplo: http://.../source?name={name}
    public ResponseEntity<?> findAllByName(
            @Valid @NotBlank @RequestParam(name = "name", defaultValue = "") String strName,
            @Valid @Positive @RequestParam(name = "page", required = false, defaultValue = "1") int nPage
    ) {
        return new ResponseEntity<>(
                service.findAllByName(strName, nPage),
                null,
                HttpStatus.OK
            );
    }

    @PostMapping()
    public ResponseEntity<?> create(
            @Valid @RequestBody SourceDto n
    ) throws JsonProcessingException {
        return new ResponseEntity<>(
                service.create(n),
                null,
                HttpStatus.CREATED
            );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(
            @Valid @Positive @PathVariable("id") Long nId,
            @Valid @RequestBody SourceDto n
    ) {
        return new ResponseEntity<>(
                service.updateById(nId, n),
                null,
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")// Ejemplo: http://.../source/{id}
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