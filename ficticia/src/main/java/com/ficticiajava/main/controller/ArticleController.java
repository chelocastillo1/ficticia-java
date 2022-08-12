package com.ficticiajava.main.controller;

import com.ficticiajava.main.dto.ArticleDto;
import com.ficticiajava.main.global.Constants;
import com.ficticiajava.main.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Validated
@RestController
@RequestMapping(value = Constants.ENDPOINT_ARTICLE)
public class ArticleController {

    private final ArticleService service;

    @Autowired
    public ArticleController(ArticleService service) {
        this.service = service;
    }

    @GetMapping()
    public ResponseEntity<?> findAll(
            @Valid @Positive @RequestParam(name = "page", required = false, defaultValue = "1") int nPage
    ) {
        return new ResponseEntity<>(
                service.findAll(nPage),
                null,
                HttpStatus.OK
        );
    }

    @GetMapping(params = {"id"}) // Ejemplo: http://.../article?id={id}
    public ResponseEntity<?> findById(
            @Valid @Positive @RequestParam(name = "id") Long nId
    ) {
        return new ResponseEntity<>(
                service.findById(nId),
                null,
                HttpStatus.OK
        );
    }

    @GetMapping(params = {"q"}) // Ejemplo: http://.../article?q=texto-a-buscar
    public ResponseEntity<?> findAllByText(
            @Valid @NotBlank @Size(min = 3, max = 255) @RequestParam(name = "q") String strLike,
            @Valid @Positive @RequestParam(name = "page", required = false, defaultValue = "1") int nPage
    ) {
        return new ResponseEntity<>(
                service.findAllByText(strLike, nPage),
                null,
                HttpStatus.OK
        );
    }

    @PostMapping()
    public ResponseEntity<?> create(
            @Valid @RequestBody ArticleDto n
    ) {
        return new ResponseEntity<>(
                service.create(n),
                null,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(
            @Valid @Positive @PathVariable("id") Long nId,
            @Valid @RequestBody ArticleDto n
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

    @PostMapping("{id}/source/{idSource}")
    public ResponseEntity<?> setSource(
            @Valid @Positive @PathVariable("id") Long nIdArticle,
            @Valid @Positive @PathVariable("idSource") Long nIdSource
    ) {
        return new ResponseEntity<>(
                service.setSource(nIdArticle, nIdSource),
                null,
                HttpStatus.OK
        );
    }

    @PostMapping("{id}/author/{idAuthor}")
    public ResponseEntity<?> setAuthor(
            @Valid @Positive @PathVariable("id") Long nIdArticle,
            @Valid @Positive @PathVariable("idAuthor") Long nIdAuthor
    ) {
        return new ResponseEntity<>(
                service.setAuthor(nIdArticle, nIdAuthor),
                null,
                HttpStatus.OK
        );
    }
}