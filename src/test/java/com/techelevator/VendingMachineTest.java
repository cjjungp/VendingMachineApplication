package com.techelevator;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.Candy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static com.techelevator.ui.UserInput.balance;
import static org.junit.Assert.assertEquals;

public class VendingMachineTest {

    private VendingMachine sut;

    @Before
    public void setup() {
        sut = new VendingMachine();
    }

    @Test

    public void Chiclets5DollarsCorrectChange() {
        // Arrange
        // Since you already have "private VendingMachine sut;" you don't need the codes below
        // VendingMachine errorDetector = new VendingMachine();

        //private String extracted(String name, BigDecimal price, BigDecimal balance, String message)

        // Act
        String name = "Chiclets";
        BigDecimal price =  new BigDecimal("1.35");
        BigDecimal balance = new BigDecimal("3.65");
        String message = "Chewy, Chewy, Lots O Bubbles!";

        String actual = sut.extracted(name, price, balance, message);
        String expected = name + " Dispensed." + " Price: $" + price + " Remaining Balance: $" + balance;

        // Assert
        // Assert.assertEquals(name + " Dispensed." + " Price: $" + price + " Remaining Balance: $" + balance, 2);
        Assert.assertEquals(expected, actual);
    }





//
//    @Test
//    public void method_returns_correct_change() {
//        // Arrange
//        correctChange sut = new correctChange();
//
//        // Act
//        boolean value;
//        String getChange = currentBalance - itemPrice;
//        String expectedResult = getChange;
//        String actualResult = sut.;
//
//        // Assert
//        Assert.assertTrue(true);
//
//        // Assert.assertEquals


    // Change

    // objects created with the right variable



//    public void will_Purchase_display_return_after_selecting_feed_money() {
//
//        // Arrange
//        System.out.println("What would you like to purchase?");
//        System.out.println();
//
//        System.out.println("(M) Feed Money");
//        System.out.println("(S) Select Item");
//        System.out.println("(F) Finish Transaction");
//
//        System.out.println();
//        System.out.print("Please select an option: ");
//
//
//        // Act
//        String expectedResult = "";
//        String actualResult = ();
//
//        // Assert
//        Assert.assertEquals(expectedResult, actualResult);
//    }


    public void will_itemCandy_display_correct_inventory_details() {

        // Arrange

//        // Act
//        String expectedResult = Candy(slotIdentifier, nameOfProduct, price, 6));
//        String actualResult = sut.
//
//        // Assert
//        Assert.assertEquals(expectedResult, actualResult);
    }

//
//     if (typeOfSnack.equals("Candy")) {
//        listOfSnacks.add(new Candy(slotIdentifier, nameOfProduct, price, 6));
//    }
//

}
