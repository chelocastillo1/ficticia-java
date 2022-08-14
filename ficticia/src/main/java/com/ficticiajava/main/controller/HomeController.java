package com.ficticiajava.main.controller;

import com.ficticiajava.main.global.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping()
    public ResponseEntity<?> home() {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping(Constants.ENDPOINT_API)
    public ResponseEntity<?> homeApi() {
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping(Constants.ENDPOINT_API_V1)
    public ResponseEntity<?> homeApiV1() {
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}