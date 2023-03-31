package com.techelevator.ui;

import com.techelevator.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;


/**
 * Responsibilities: This class should handle formatting and displaying ALL
 * messages to the user
 * 
 * Dependencies: None
 */
public class UserOutput
{
    public static int balance = 0;
//    private Map<String, String> testMap = new HashMap<>();

    public static void displayMessage(String message)
    {
        System.out.println();
        System.out.println(message);
        System.out.println();
    }

    public static void displayHomeScreen()
    {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Home");
        System.out.println("***************************************************");
        System.out.println();
    }

    public static void displayMainMenu(){
        System.out.println();
        System.out.println("(D) Display Vending Items");
        System.out.println("(P) Purchase");
        System.out.println("(E) Exit");
    }

    public static void displayPurchaseScreen() {
        System.out.println();
        System.out.println("(M) Feed Money");
        System.out.println("(S) Select Item");
        System.out.println("(F) Finish Transaction");
        System.out.println();
        System.out.println("Current Money Provided: " + (double)balance);
    }

    // Just like the HomeScreen
    public static void displayPurchaseScreenOpening()
    {
        System.out.println();
        System.out.println("***************************************************");
        System.out.println("                      Purchase Option");
        System.out.println("***************************************************");
        System.out.println();
    }


}
