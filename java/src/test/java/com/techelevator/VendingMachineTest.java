package com.techelevator;

import com.techelevator.application.VendingMachine;
import com.techelevator.models.Candy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class VendingMachineTest {

    private VendingMachine sut;

    @Before

    public void setup() {
        sut = new VendingMachine();

    }


    @Test


    // Change


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

        // Act
        String expectedResult = Candy(slotIdentifier, nameOfProduct, price, 6));
        String actualResult = sut.

        // Assert
        Assert.assertEquals(expectedResult, actualResult);
    }

//
//     if (typeOfSnack.equals("Candy")) {
//        listOfSnacks.add(new Candy(slotIdentifier, nameOfProduct, price, 6));
//    }
//

}
