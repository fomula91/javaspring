package com.group.librayapp.dto.fruit.request;

public class UpdateFruitRequest {
    private long id;
    private String name;
    private long price;
    private boolean isSold;
    public boolean isSold() {
        return isSold;
    }
    public void setSold(boolean isSold) {
        this.isSold = isSold;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getPrice() {
        return price;
    }
    public void setPrice(long price) {
        this.price = price;
    }
}
