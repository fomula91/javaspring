package com.group.librayapp.controller.fruit;

import org.springframework.web.bind.annotation.RestController;

import com.group.librayapp.dto.fruit.request.CereateFruitReqeust;
import com.group.librayapp.dto.fruit.request.UpdateFruitRequest;
import com.group.librayapp.dto.fruit.response.FruitResponse;
import com.group.librayapp.service.fruit.FruitService;

import java.util.List;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class FruitController {

    private final FruitService fruitService;
    // private static final Logger logger = LoggerFactory.getLogger(FruitController.class);

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @PostMapping("/api/v1/fruit")
    public void postFruitSet(@RequestBody  CereateFruitReqeust request) {
        fruitService.postFruitSet(request);        
    }
        

    @PutMapping("/api/v1/fruit")
    public String putFruitSet(@RequestBody UpdateFruitRequest request) {
        return fruitService.putFruitSet(request);
    }

    @GetMapping("/api/v1/fruit/stat")
    public List<FruitResponse> getFruitStatus() {
        return fruitService.getFruitStatus();
        
    }
    
}

