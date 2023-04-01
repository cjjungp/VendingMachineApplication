package com.techelevator.models;

import java.math.BigDecimal;

public class Drink extends Snack{
    public Drink(String slotID, String name, BigDecimal price) {
        super(slotID, name, price);
    }

    @Override
    public String getMessage() {
        return "Drinky, Drinky, Slurp Slurp!";
    }
}
