package com.techelevator.models;

import java.math.BigDecimal;

public class Munchy extends Snack {
    public Munchy(String slotID, String name, BigDecimal price) {
        super(slotID, name, price);
    }

    @Override
    public String getMessage() {
        return "Munchy, Munchy, so Good!";
    }
}
