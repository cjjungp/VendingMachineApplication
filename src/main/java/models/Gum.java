package com.techelevator.models;

import java.math.BigDecimal;

public class Gum extends Snack{
    public Gum(String slotID, String name, BigDecimal price) {
        super(slotID, name, price);
    }

    @Override
    public String getMessage() {
        return "Chewy, Chewy, Lots O Bubbles!";
    }
}
