package com.techelevator.models;

import java.math.BigDecimal;

public class Candy extends Snack{
    public Candy(String slotID, String name, BigDecimal price, int stock) {
        super(slotID, name, price, stock);
    }
}
