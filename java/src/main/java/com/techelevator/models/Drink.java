package com.techelevator.models;

import java.math.BigDecimal;

public class Drink extends Snack{
    public Drink(String slotID, String name, BigDecimal price, int stock) {
        super(slotID, name, price, stock);
    }
}
