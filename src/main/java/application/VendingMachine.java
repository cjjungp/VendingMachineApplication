package com.techelevator.application;

import com.techelevator.logger.Logger;
import com.techelevator.models.*;
import com.techelevator.ui.UserInput;
import com.techelevator.ui.UserOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class VendingMachine {
    private Scanner scanner = new Scanner(System.in);
    private UserInput input = new UserInput();
    private UserOutput output = new UserOutput();
    private Logger logger = new Logger("Audit.txt");
    // Audit Entry
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
    // No Items left
    private int NO_LONGER_AVAILABLE = 0;
    private static List<Snack> listOfSnacks = new ArrayList<>();


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
    private void handlePurchaseMenuOptions() {

        boolean stay = true;
        boolean hasDiscount = false;

        do {

                String choice = UserInput.getDisplayPurchaseOptions();

            if (choice.equals("feed money")) {
                System.out.println("Please insert money in whole amount ($1, $5, $10, or $20)");
                System.out.println("Current Money Provided: " + UserInput.balance.setScale(2, RoundingMode.HALF_UP));
                String moneyAdded = scanner.nextLine();
                double num = Double.parseDouble(moneyAdded);
                BigDecimal numMoneyAdded = BigDecimal.valueOf(num);
                UserInput.balance = (UserInput.balance.setScale(2, RoundingMode.HALF_UP).add(numMoneyAdded.setScale(2, RoundingMode.HALF_UP)));

                // Used audit.write to log when money is inserted.
//                audit.write(dateTimeFormatter.format(LocalDateTime.now()) + " MONEY FED:           $" + numMoneyAdded.setScale(2,RoundingMode.HALF_UP) + " $" + UserInput.balance.setScale(2, RoundingMode.HALF_UP));
                logger.write(String.format( "%s %-20s $%s  $%-5s", dateTimeFormatter.format(LocalDateTime.now()), "MONEY FED: ", numMoneyAdded.setScale(2, RoundingMode.HALF_UP), UserInput.balance));
//                String test = dateTimeFormatter.format(LocalDateTime.now());
//                audit.write(String.format("T"));
            }

            // still need a discount
            if(choice.equals("select item")) {

                System.out.println();
                System.out.println("Please select an item to purchase");
                displayVendingItems1();
                String userSelectedID = scanner.nextLine();

                for (Snack snacks : listOfSnacks) {
                    if (snacks.getSlotID().toLowerCase().equals(userSelectedID.toLowerCase()) && snacks.getStock() > 0 && UserInput.balance.setScale(2, RoundingMode.HALF_UP).compareTo(snacks.getPrice()) >= 0) {



                        BigDecimal oldBalance = UserInput.balance;

                        // need to add an if statement for munchy munchy/drinky drinky/etc.
                        // encapsulation (look below for full description of the method ["extracted")
                        int currentStock = snacks.getStock();
                        snacks.setStock(currentStock - 1);
//                        UserInput.balance = UserInput.balance.setScale(2, RoundingMode.HALF_UP).subtract(snacks.getPrice());

//                        extracted(snacks.getName(),snacks.getPrice(),UserInput.balance.setScale(2, RoundingMode.HALF_UP),snacks.getMessage());

                        BigDecimal discount = new BigDecimal(-1.00);
                        if (hasDiscount) {

//                            snacks.setPrice(snacks.getPrice().add(discount));
                            BigDecimal currentSnackPrice = snacks.getPrice();
                            BigDecimal adjustedSnackPrice = currentSnackPrice.add(discount);
                            UserInput.balance = UserInput.balance.setScale(2, RoundingMode.HALF_UP).subtract(adjustedSnackPrice);
                            extracted(snacks.getName(),snacks.getPrice().add(discount),UserInput.balance.setScale(2, RoundingMode.HALF_UP),snacks.getMessage());

                            hasDiscount = false;
                        } else if (!hasDiscount) {
                            BigDecimal currentSnackPrice = snacks.getPrice();
                            UserInput.balance = UserInput.balance.setScale(2, RoundingMode.HALF_UP).subtract(currentSnackPrice);
                            extracted(snacks.getName(),snacks.getPrice(),UserInput.balance.setScale(2, RoundingMode.HALF_UP),snacks.getMessage());
                            hasDiscount = true;
                        }

                        logger.write(String.format( "%s %-17s %s $%-5s $%-5s", dateTimeFormatter.format(LocalDateTime.now()), snacks.getName(), snacks.getSlotID(), oldBalance, UserInput.balance   ));

                        break;
                    } if (snacks.getSlotID().toLowerCase().equals(userSelectedID.toLowerCase()) && UserInput.balance.setScale(2, RoundingMode.HALF_UP).compareTo(snacks.getPrice()) <= 0 && snacks.getStock() > 0) {
                        System.out.println("Not Enough Money");
                    } if (snacks.getSlotID().toLowerCase().equals(userSelectedID.toLowerCase()) && snacks.getStock() == 0) {
                        System.out.println("NO LONGER AVAILABLE");
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
                BigDecimal originalBalance = UserInput.balance.setScale(2, RoundingMode.HALF_UP);

                int dollarCounter = 0;
                int quarterCounter = 0;
                int dimeCounter = 0;
                int nickelCounter = 0;

                if (UserInput.balance.setScale(2, RoundingMode.HALF_UP).compareTo(zero) > 0) {
                    while (UserInput.balance.setScale(2).compareTo(oneDollar) >= 0) {
                        dollarCounter++;
                        UserInput.balance = UserInput.balance.setScale(2, RoundingMode.HALF_UP).subtract(oneDollar.setScale(2, RoundingMode.HALF_UP));
                    }
                    while (UserInput.balance.setScale(2, RoundingMode.HALF_UP).compareTo(oneQuarter.setScale(2, RoundingMode.HALF_UP)) >= 0) {
                        quarterCounter++;
                        UserInput.balance = UserInput.balance.setScale(2, RoundingMode.HALF_UP).subtract(oneQuarter.setScale(2, RoundingMode.HALF_UP));
                    }
                    while (UserInput.balance.setScale(2, RoundingMode.HALF_UP).compareTo(oneDime.setScale(2, RoundingMode.HALF_UP)) >= 0) {
                        dimeCounter++;
                        UserInput.balance = UserInput.balance.setScale(2, RoundingMode.HALF_UP).subtract(oneDime.setScale(2, RoundingMode.HALF_UP));
                    }
                    while (UserInput.balance.setScale(2, RoundingMode.HALF_UP).compareTo(oneNickel.setScale(2, RoundingMode.HALF_UP)) >= 0) {
                        nickelCounter++;
                        UserInput.balance = UserInput.balance.setScale(2, RoundingMode.HALF_UP).subtract(oneNickel.setScale(2, RoundingMode.HALF_UP));
                    }
                }
                System.out.println("Your Change = Dollars: " + dollarCounter + ", Quarters: " + quarterCounter + ", Dimes: " + dimeCounter + ", Nickels: " + nickelCounter);
//                audit.write(dateTimeFormatter.format(LocalDateTime.now()) + " CHANGE GIVEN: " + originalBalance + " " + UserInput.balance);
                String changeGiven = "CHANGE GIVEN: ";

                logger.write(String.format( "%s %-20s $%s  $%s ", dateTimeFormatter.format(LocalDateTime.now()), changeGiven, originalBalance, UserInput.balance));
            }
            if(choice.equals("q")) {
                stay = false;
            }
        } while (stay);
    }

    public String extracted(String name, BigDecimal price, BigDecimal balance, String message) {
        System.out.println(name + " Dispensed." + " Price: $" + price + " Remaining Balance: $" + balance);
        System.out.println();
        // using abstract class method (the quo ted message is included in the subclass itself "Munchy, Munchy, so Good!"
        System.out.println(message);
        System.out.println();

        return name + " Dispensed." + " Price: $" + price + " Remaining Balance: $" + balance;
    }


    // Might be able to move this to UserOutput Class?? ...
    // ...I have not tried yet since I got it working.
    private void displayVendingItems() {

        System.out.println();
        for (Snack snacks : listOfSnacks) {
            System.out.println(snacks.getSlotID() + ": " + snacks.getName() + " - $" + snacks.getPrice() + " - Stock: " + snacks.getStock());
        }
    }

    private void displayVendingItems1() {
        for (Snack snacks : listOfSnacks) {
            System.out.println(snacks.getSlotID() + ": " + snacks.getName() + " - $" + snacks.getPrice() + " - Stock: " + snacks.getStock());
        }
    }


    private List readFile() {

//        List<Snack> listOfSnacks = new ArrayList<>();
        File file = new File("catering1.csv");

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
