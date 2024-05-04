package com.group.librayapp.dto.fruit.request;

import java.time.LocalDate;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class CereateFruitReqeust {

    @NotBlank(message = "name is required")
    private String name;
    @NotNull(message = "werehousingDate is required")
    private LocalDate werehousingDate;
    @Min(value = 1, message = "price must be greater than 0")
    private long price;
    
    public void setName(String name) {
        this.name = name;
    }
    public void setWerehousingDate(LocalDate werehousingDate) {
        this.werehousingDate = werehousingDate;
    }
    public void setPrice(long price) {
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public LocalDate getWerehousingDate() {
        return werehousingDate;
    }
    public long getPrice() {
        return price;
    }
    
}
