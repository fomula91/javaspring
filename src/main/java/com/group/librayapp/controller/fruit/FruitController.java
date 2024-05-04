package com.group.librayapp.controller.fruit;

import org.springframework.web.bind.annotation.RestController;

import com.group.librayapp.dto.fruit.request.CereateFruitReqeust;
import com.group.librayapp.dto.fruit.request.UpdateFruitRequest;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class FruitController {

    private final JdbcTemplate jdbcTemplate;
    // private static final Logger logger = LoggerFactory.getLogger(FruitController.class);

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

    @PutMapping("/api/v1/fruit")
    public String putFruitSet(@RequestBody UpdateFruitRequest request) {
        String getId = "SELECT * FROM fruit WHERE id = ?";
        Boolean isFruiltExist = jdbcTemplate.query(getId, (rs, rowNum) -> 0, request.getId()).isEmpty();
        //test make logger
        // logger.info("Fruit is exist: " + isFruiltExist);
        if (isFruiltExist) {
            throw new FruitNotFonudException("Fruit with ID " + request.getId() + " does not exist.");
        }

        String sql = "UPDATE fruit SET is_sold = ? WHERE id = ?";
        jdbcTemplate.update(sql, true, request.getId());
        
        return request.getName();
    }

    public class FruitNotFonudException extends RuntimeException {
        public FruitNotFonudException(String message) {
            super(message);
        }
    }

    @ExceptionHandler(FruitNotFonudException.class)
    public ResponseEntity<String> handleFruitNotFoundException(FruitNotFonudException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}

