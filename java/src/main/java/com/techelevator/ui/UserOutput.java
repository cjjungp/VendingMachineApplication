package com.techelevator.ui;

import com.techelevator.models.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Responsibilities: This class should handle formatting and displaying ALL
 * messages to the user
 * 
 * Dependencies: None
 */
public class UserOutput
{
    public static int balance = 0;

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

    public static Map<String, Snack> readFile() {
        // create a map that contains the stock, Key = slotID value+ snackName
        Map<String, Snack> stock = new HashMap<>();

        // use this file path to see whats in stock
        File file = new File("catering.csv");

        if (!file.exists()) {
            System.out.println("Error reading file - program exiting!");
            System.exit(0);
        }
        //fill the map
        try (Scanner reader = new Scanner(file)) {

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String [] splitLine = line.split(",");
                // this is the Slot
                String slotIdentifier = splitLine[0];
                String nameOfProduct = splitLine[1];
                BigDecimal price = new BigDecimal(splitLine[2]);
                String typeOfSnack = splitLine[3];

                // create the new snack and add it to the map
                if (typeOfSnack.equals("Candy")) {
                    stock.put(slotIdentifier, new Candy(nameOfProduct, price, 6));
                }
                //do the same for each type of snack
                if (typeOfSnack.equals("Drink")) {
                    stock.put(nameOfProduct, new Drink(nameOfProduct, price, 6));
                }
                if (typeOfSnack.equals("Gum")) {
                    stock.put(nameOfProduct, new Gum(nameOfProduct, price, 6));
                }
                if (typeOfSnack.equals("Munchy")) {
                    stock.put(nameOfProduct, new Munchy(nameOfProduct, price, 6));
                }


            }

        } catch (FileNotFoundException e) {
            System.out.println("Error");
            System.exit(0);
        }
        return stock;
    }

    public static void displayVendingItems (){
        // Map<String, Snack> stock = new HashMap<>();
        Map mapOfStock = readFile();
        // format the display when the User presses (D)

        // Slot ID, name of item, price, stock
        // Slot ID, name of item, price, stock

//        for (Object map : mapOfStock.keySet()) {
//            System.out.println(mapOfStock.get());
//        }


    }



}
