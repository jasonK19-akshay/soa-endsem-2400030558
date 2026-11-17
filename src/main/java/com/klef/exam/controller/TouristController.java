package com.klef.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.klef.exam.entity.Tourist;
import com.klef.exam.repository.TouristRepository;

@RestController
@RequestMapping("/api/tourists")
public class TouristController {

    @Autowired
    private TouristRepository repository;

    @Value("${tourist.valid-token}")
    private String validToken;

    @PostMapping
    public ResponseEntity<?> addTourist(
            @RequestHeader(value = "token", required = false)
            String token,
            @RequestBody Tourist tourist) {

        if (token == null || !token.equals(validToken)) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("token validation failed");
        }

        tourist.setId(null);
        Tourist saved = repository.save(tourist);

        return ResponseEntity.ok(saved);
    }
}