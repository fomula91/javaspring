package com.group.librayapp.controller.calculator;

import org.springframework.web.bind.annotation.RestController;

import com.group.librayapp.dto.calculator.CalculatorAddRequest;
import com.group.librayapp.dto.calculator.CalculatorAllSum;
import com.group.librayapp.dto.calculator.CalculatorMultiplyReqeust;
import com.group.librayapp.dto.date.DateRequest;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
public class CalculatorController {
    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request) {
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyReqeust request) {
        return request.getNumber1() * request.getNumber2();
    }

    @GetMapping("/api/v1/calc")
    public Map<String, Object> getCalc(CalculatorAddRequest request) {
        Map<String, Object> result = new HashMap<>();
        result.put("add", request.getNumber1() + request.getNumber2());
        result.put("minus", request.getNumber1() - request.getNumber2());
        result.put("multiply", request.getNumber1() * request.getNumber2());

        return result;
    }

    @GetMapping("/api/v1/day-of-week")
    public Map<String, Object> getDayofweek(DateRequest request) {
        LocalDate date = LocalDate.parse(request.getDate());
        String currentDate = date.getDayOfWeek().toString().substring(0,3).toUpperCase();
        Map<String, Object> result = new HashMap<>();
        result.put("dayOfTheWeek", currentDate);

        return result;
    }

    
    @PostMapping("/postnumbers")
    public Integer postCalculatorNumbers(@RequestBody CalculatorAllSum reqeust) {
        Integer Result = 0;
        for(int i = 0; i < reqeust.getNumbers().size(); i++) {
            Result = Result + reqeust.getNumbers().get(i);
        }
        return Result;
    }
    
    
    
}
