package com.group.librayapp.controller.calculator;

import org.springframework.web.bind.annotation.RestController;

import com.group.librayapp.dto.calculator.CalculatorAddRequest;
import com.group.librayapp.dto.calculator.CalculatorMultiplyReqeust;

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
    
}
