package com.techelevator.application;

import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;



public class VendingMachine {
    private List<Snack> listOfSnacks = new ArrayList<>();

    public void run() {
        while (true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if (choice.equals("display")) {
                displayVendingItems();

                // display the vending machine slots
            } else if (choice.equals("purchase")) {
                // make a purchase
            } else if (choice.equals("exit")) {
                // good bye
                break;
            }
        }
    }

    // Might be able to move this to UserOutput Class?? ...
    // ...I have not tried yet since I got it working.
    public void displayVendingItems() {

        this.listOfSnacks = readFile();
        System.out.println();
        for (Snack snacks : listOfSnacks) {
            System.out.println(snacks.getSlotID() + ": " + snacks.getName() + " - $" + snacks.getPrice() + " - Stock: " + snacks.getStock());
        }
    }

    public static List readFile() {

        List<Snack> listOfSnacks = new ArrayList<>();
        File file = new File("catering.csv");

        if (!file.exists()) {
            System.out.println("Error reading file - program exiting!");
            System.exit(0);
        }
        try (Scanner reader = new Scanner(file)) {

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String [] splitLine = line.split(",");
                // this is the Slot
                String slotIdentifier = splitLine[0];
                String nameOfProduct = splitLine[1];
                BigDecimal price = new BigDecimal(splitLine[2]);
                String typeOfSnack = splitLine[3];

                if (typeOfSnack.equals("Candy")) {
                    listOfSnacks.add(new Candy(slotIdentifier, nameOfProduct, price, 6));
                }

                if (typeOfSnack.equals("Drink")) {
                    listOfSnacks.add(new Drink(slotIdentifier, nameOfProduct, price, 6));
                }

                if (typeOfSnack.equals("Gum")) {
                    listOfSnacks.add(new Gum(slotIdentifier, nameOfProduct, price, 6));
                }

                if (typeOfSnack.equals("Munchy")) {
                    listOfSnacks.add(new Munchy(slotIdentifier, nameOfProduct, price, 6));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            System.exit(0);
        }
        return listOfSnacks;
    }
}
