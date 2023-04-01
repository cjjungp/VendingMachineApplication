package com.techelevator.application;

import com.techelevator.logger.Audit;
import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.sql.Array;
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

    private static List<Snack> listOfSnacks = new ArrayList<>();
    private List<Snack> listOfSnacks1 = new ArrayList<>();
    

    public void run() {
        readFile();
        while (true) {
            UserOutput.displayHomeScreen();
            String choice = UserInput.getHomeScreenOption();

            if (choice.equals("display")) {
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
                String choice = UserInput.getDisplayPurchaseOptions();

            if (choice.equals("feed money")) {
                System.out.println("Please insert money in whole amount ($1, $5, $10, or $20)");
                System.out.println("Current Money Provided: " + UserInput.balance);
                String moneyAdded = scanner.nextLine();
                double num = Double.parseDouble(moneyAdded);
                BigDecimal numMoneyAdded = BigDecimal.valueOf(num);
                UserInput.balance = (UserInput.balance.add(numMoneyAdded));

                // Used audit.write to log when money is inserted.
                audit.write(dateTimeFormatter.format(LocalDateTime.now()) + " MONEY FED:          $" + numMoneyAdded + "   $" + UserInput.balance);
            }

            // still need a discount
            if(choice.equals("select item")) {

                System.out.println();
                System.out.println("Please select an item to purchase");
                displayVendingItems1();
                String userSelectedID = scanner.nextLine();

                for (Snack snacks : listOfSnacks) {
                    if (snacks.getSlotID().toLowerCase().equals(userSelectedID.toLowerCase()) && snacks.getStock() > 0 && UserInput.balance.compareTo(snacks.getPrice()) >= 0) {
                        int currentStock = snacks.getStock();
                        snacks.setStock(currentStock - 1);


                        UserInput.balance = UserInput.balance.subtract(snacks.getPrice());
                        // $5 in, buy d4 two times leaves the user with 2.30 leftover.
                        // if discount is working correctly, there should be 2.975 leftover after purchasing two d4


                        // -------------------------------------------------------------------

                        // this works to apply a half off discount, but I can't figure out how to make it happen every other time
                        // UserInput.balance = UserInput.balance.subtract( (snacks.getPrice().divide(divideBy2)) );


                        // ---------------- this didn't quite work --------------------------

//                        int counter = 0;
//                        if (counter == 0) {
//                            UserInput.balance = UserInput.balance.subtract( snacks.getPrice() );
//                            counter = counter + 1;
//
//                        } else {
//                            BigDecimal divideBy2 = new BigDecimal(2);
//                            UserInput.balance = UserInput.balance.subtract( (snacks.getPrice().divide(divideBy2)) );
//                        }

                        // ----------------- neither did this -------------------------------

//                        if (hasDiscount == true) {
//                            BigDecimal divideBy2 = new BigDecimal(2);
//                            UserInput.balance = UserInput.balance.subtract( (snacks.getPrice().divide(divideBy2)) );
//
//                            hasDiscount = false;
//
//                        } else if(hasDiscount == false) {
//
//                            UserInput.balance = UserInput.balance.subtract( snacks.getPrice() );
//                            hasDiscount = true;
//                        }
                        // ------------------------------------------------------------------------


                        // need to add an if statement for munchy munchy/drinky drinky/etc.
                        System.out.println(snacks.getName() + " Dispensed." + " Price: $" + snacks.getPrice() + " Remaining Balance: $" + UserInput.balance);
                        System.out.println();
                        // using abstract class method (the quo ted message is included in the subclass itself "Munchy, Munchy, so Good!"
                        System.out.println(snacks.getMessage());
                        System.out.println();



//                        if (snacks.getSnacksType().equals(Munchy)) {
//                            System.out.println(Munchy);
//                        }
//
//                        if (snacks.getSnacksType() == Candy) {
//                            System.out.println(Candy);
//                        }
//
//                        if (snacks.getSnacksType() == Drink) {
//                            System.out.println(Drink);
//                        }
//
//                        if (snacks.getSnacksType() == Gum) {
//                            System.out.println(Gum);
//                        }


                        BigDecimal snack = snacks.getPrice();
                        BigDecimal originalBalance = snack.add(UserInput.balance);
                        BigDecimal newBalance = UserInput.balance;

                        audit.write(dateTimeFormatter.format(LocalDateTime.now()) + " " + snacks.getName() + " " + snacks.getSlotID() + "         $" + originalBalance + "   $" + newBalance);


                        break;
                    } if (snacks.getSlotID().toLowerCase().equals(userSelectedID.toLowerCase()) && UserInput.balance.compareTo(snacks.getPrice()) <= 0 && snacks.getStock() > 0) {
                        System.out.println("Not Enough Money");
                    } if (snacks.getSlotID().toLowerCase().equals(userSelectedID.toLowerCase()) && snacks.getStock() == 0) {
                        System.out.println("Out of stock.");
                    }


                }
            }



            if (choice.equals("finish transaction")){
                    System.out.println();
                    System.out.println("Thank you. Please don't forget your change if there is any.");
                BigDecimal zero = new BigDecimal(0);
                BigDecimal oneDollar = new BigDecimal(1);
                BigDecimal oneQuarter = new BigDecimal(0.25);
                BigDecimal oneDime = new BigDecimal(0.10);
                BigDecimal oneNickel = new BigDecimal(0.05);

                int dollarCounter = 0;
                int quarterCounter = 0;
                int dimeCounter = 0;
                int nickelCounter = 0;

                if (UserInput.balance.compareTo(zero) > 0) {
                    while (UserInput.balance.compareTo(oneDollar) >= 0) {
                        dollarCounter++;
                        UserInput.balance = UserInput.balance.subtract(oneDollar);
                    }
                    while (UserInput.balance.compareTo(oneQuarter) >= 0) {
                        quarterCounter++;
                        UserInput.balance = UserInput.balance.subtract(oneQuarter);
                    }
                    while (UserInput.balance.compareTo(oneDime) >= 0) {
                        dimeCounter++;
                        UserInput.balance = UserInput.balance.subtract(oneDime);
                    }
                    while (UserInput.balance.compareTo(oneNickel) >= 0) {
                        nickelCounter++;
                        UserInput.balance = UserInput.balance.subtract(oneNickel);
                    }
                }
                System.out.println("Your Change = Dollars: " + dollarCounter + " Quarters: " + quarterCounter + " Dimes: " + dimeCounter + " Nickels: " + nickelCounter);
                }
            if(choice.equals("q")) {
                stay = false;
            }
        } while (stay);
    }


    // Might be able to move this to UserOutput Class?? ...
    // ...I have not tried yet since I got it working.
    public void displayVendingItems() {

//        this.listOfSnacks = readFile();
        listOfSnacks1 = listOfSnacks;
        System.out.println();
        for (Snack snacks : listOfSnacks) {
            System.out.println(snacks.getSlotID() + ": " + snacks.getName() + " - $" + snacks.getPrice() + " - Stock: " + snacks.getStock());
        }
    }

    public void displayVendingItems1() {
        for (Snack snacks : listOfSnacks) {
            System.out.println(snacks.getSlotID() + ": " + snacks.getName() + " - $" + snacks.getPrice() + " - Stock: " + snacks.getStock());
        }
    }


    public List readFile() {

//        List<Snack> listOfSnacks = new ArrayList<>();
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
                    listOfSnacks.add(new Candy(slotIdentifier, nameOfProduct, price));
                }

                if (typeOfSnack.equals("Drink")) {
                    listOfSnacks.add(new Drink(slotIdentifier, nameOfProduct, price));
                }

                if (typeOfSnack.equals("Gum")) {
                    listOfSnacks.add(new Gum(slotIdentifier, nameOfProduct, price));
                }

                if (typeOfSnack.equals("Munchy")) {
                    listOfSnacks.add(new Munchy(slotIdentifier, nameOfProduct, price));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error");
            System.exit(0);
        }
        return listOfSnacks;
    }



}
