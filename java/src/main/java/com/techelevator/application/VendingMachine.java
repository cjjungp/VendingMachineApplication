package com.techelevator.application;

import com.techelevator.logger.Audit;
import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;



public class VendingMachine {
    private Scanner scanner = new Scanner(System.in);
    private UserInput input = new UserInput();
    private UserOutput output = new UserOutput();
    private Audit audit = new Audit("Audit.txt");
    // Audit Entry
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
    // No Items left
    private int NO_LONGER_AVAILABLE = 0;

    private List<Snack> listOfSnacks = new ArrayList<>();


    public void run() {
        while (true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if (choice.equals("display")) {
                // Audit Entry started -- need to implement CHOSEN ITEM/ACTION, INPUT BALANCE, FINAL BALANCE
                audit.write(dateTimeFormatter.format(LocalDateTime.now()) + "  -User selected Display - ");
                displayVendingItems();

                // display the vending machine slots
            } if (choice.equals("purchase")) {

                handlePurchaseMenuOptions();
            }

            if (choice.equals("exit")) {
                // good bye
                break;


            }
        }
    }
    public void handlePurchaseMenuOptions() {

        boolean stay = true;

        do {
//            System.out.println("----Purchase option selected----");
                audit.write(dateTimeFormatter.format(LocalDateTime.now()) + " - User selected Purchase - ");
//                UserOutput.displayPurchaseScreenOpening();
                String choice = UserInput.getDisplayPurchaseOptions();

            if (choice.equals("feed money")) {
                System.out.println("Please insert money in whole amount ($1, $5, $10, or $20)");
                System.out.println("Current Money Provided: " + UserInput.balance);
                String moneyAdded = scanner.nextLine();
                double num = Double.parseDouble(moneyAdded);
                BigDecimal numMoneyAdded = BigDecimal.valueOf(num);
                UserInput.balance = (UserInput.balance.add(numMoneyAdded));
            }
            if(choice.equals("select item")) {

                System.out.println();
                System.out.println("Please select an item to purchase");
                displayVendingItems();
                String userSelectedID = scanner.nextLine();

                for (Snack snacks : listOfSnacks) {
                    if (snacks.getSlotID().toLowerCase().equals(userSelectedID.toLowerCase())) {
                        System.out.println(snacks.getName());
                        UserInput.balance = UserInput.balance.subtract(snacks.getPrice());

                        // update stock...

                        
                        int currentStock = snacks.getStock();
                        snacks.setStock(currentStock - 1);
                    }
                }

            }
            if (choice.equals("finish transaction")){
                    System.out.println();
                    System.out.println("Thank you. Please don't forget your change if there is any");
                }
            if(choice.equals("q")) {
                stay = false;
            }
        } while (stay);
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


    // If you run out of stock.....
//                    int numberOfSnacksPurchased = 0;
//            if (snacks.getStock() == NO_LONGER_AVAILABLE) {
//                System.out.println(NO_LONGER_AVAILABLE);
//            } else if (numberOfSnacksPurchased > 0) {
//                System.out.println(listOfSnacks.size() - numberOfSnacksPurchased);
//            } else {



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
