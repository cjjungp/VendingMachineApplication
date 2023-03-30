package com.techelevator.models;

import java.math.BigDecimal;

public abstract class Snack {
    private String slotID;
    private String name;
    private BigDecimal price;
    private int stock;


    public Snack(String slotID, String name, BigDecimal price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.slotID = slotID;
    }

    public String getSlotID() {
        return slotID;
    }
    public void setSlotID() {
        this.slotID = slotID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
