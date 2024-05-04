package com.group.librayapp.dto.fruit.response;

public class FruitResponse {
    private long selesAmount;
    private long notSalesAmount;

    public FruitResponse(long selesAmount, long notSalesAmount) {
        this.selesAmount = selesAmount;
        this.notSalesAmount = notSalesAmount;
    }

    public long getSelesAmount() {
        return selesAmount;
    }
    public void setSelesAmount(long selesAmount) {
        this.selesAmount = selesAmount;
    }
    public long getNotSalesAmount() {
        return notSalesAmount;
    }
    public void setNotSalesAmount(long notSalesAmount) {
        this.notSalesAmount = notSalesAmount;
    }


}
