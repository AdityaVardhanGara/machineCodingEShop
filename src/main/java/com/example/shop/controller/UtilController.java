package com.example.shop.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UtilController {
    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return new ResponseEntity<>("HELLO", HttpStatusCode.valueOf(200));
    }
}
