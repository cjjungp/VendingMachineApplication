package com.techelevator.models;

import java.math.BigDecimal;

public class Candy extends Snack{
    public Candy(String slotID, String name, BigDecimal price) {
        super(slotID, name, price);
    }

    @Override
    public String getMessage() {
        return "Sugar, Sugar, so Sweet!";
    }
}
