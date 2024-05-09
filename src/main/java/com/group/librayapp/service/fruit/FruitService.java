package com.group.librayapp.service.fruit;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.group.librayapp.dto.fruit.request.CereateFruitReqeust;
import com.group.librayapp.dto.fruit.request.UpdateFruitRequest;
import com.group.librayapp.dto.fruit.response.FruitResponse;
import com.group.librayapp.repository.fruit.FruitRepository;


@Service
public class FruitService {
    
    private final FruitRepository fruitRepository;    

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public void postFruitSet(@Validated CereateFruitReqeust request) {
        fruitRepository.postFruitSet(request);
    }

    public String putFruitSet(UpdateFruitRequest request) {
        if (fruitRepository.isFruitExist(request.getId())) {
            throw new FruitNotFonudException("Fruit with ID " + request.getId() + " does not exist.");
        }

        fruitRepository.putFruitSet(request);
        
        return request.getName();
    }

    public List<FruitResponse> getFruitStatus() {
        return fruitRepository.getFruitStatus();
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
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = "Validation failed for request";

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(errorMessage);
    }

}
