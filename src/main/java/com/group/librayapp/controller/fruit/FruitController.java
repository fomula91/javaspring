package com.group.librayapp.controller.fruit;

import org.springframework.web.bind.annotation.RestController;

import com.group.librayapp.dto.fruit.request.CereateFruitReqeust;
import com.group.librayapp.dto.fruit.request.UpdateFruitRequest;
import com.group.librayapp.dto.fruit.response.FruitResponse;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @GetMapping("/api/v1/fruit/stat")
    public List<FruitResponse> getMethodName() {
        String sql = "SELECT SUM(CASE WHEN is_sold = 1 THEN price ELSE 0 END) AS seles_amount, SUM(CASE WHEN is_sold = 0 OR is_sold IS NULL THEN price ELSE 0 END) AS not_sales_amount FROM fruit";

        return jdbcTemplate.query(sql, new RowMapper<FruitResponse>() {
            @Override
            public FruitResponse mapRow(ResultSet rs, int rowNum) throws SQLException{ 
                long selesAmount = rs.getLong("seles_amount");
                long notSalesAmount = rs.getLong("not_sales_amount");
                return new FruitResponse(selesAmount, notSalesAmount);
            }
        });
        
    }
    
}

