package com.group.librayapp.controller.fruit;

import org.springframework.web.bind.annotation.RestController;

import com.group.librayapp.dto.fruit.request.CereateFruitReqeust;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class FruitController {

    private final JdbcTemplate jdbcTemplate;

    public FruitController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/api/v1/fruit")
    public void postFruitSet(@RequestBody @Validated CereateFruitReqeust request) {
        
        String sql = "INSERT INTO fruit (name, werehousing_date, price) VALUES (?, ?, ?)";

        jdbcTemplate.update(sql, request.getName(), request.getWerehousingDate(), request.getPrice());
    }
        
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = "Validation failed for request";

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorMessage);
    }
}
