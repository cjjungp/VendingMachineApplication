package com.techelevator.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Scanner;

/**
 * Responsibilities: This class should handle receiving ALL input from the User
 * 
 * Dependencies: None
 */
public class UserInput
{
    private static Scanner scanner = new Scanner(System.in);
    public static BigDecimal balance = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);


    public BigDecimal getBalance(){
        return balance.setScale(2, RoundingMode.HALF_UP);
    }

    public static String getHomeScreenOption()
    {
        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("D) Display Vending Machine Items");
        System.out.println("P) Purchase");
        System.out.println("E) Exit");

        System.out.println();
        System.out.print("Please select an option: ");

        String selectedOption = scanner.nextLine().toLowerCase();
        String option = selectedOption.trim();

        if (option.equals("d"))
        {
            return "display";
        }
        else if (option.equals("p"))
        {
            return "purchase";
        }
        else if (option.equals("e"))
        {
            return "exit";
        }
        else
        {
            return "";
        }

    }



    public static String getDisplayPurchaseOptions() {

        System.out.println("What would you like to do?");
        System.out.println();

        System.out.println("(M) Feed Money");
        System.out.println("(S) Select Item");
        System.out.println("(F) Finish Transaction");
        System.out.println("(Q) Return to Main Menu");

        System.out.println();
        System.out.println("Current Money Provided: $" + balance.setScale(2, RoundingMode.HALF_UP));

        System.out.println();
        System.out.print("Please select an option: ");


        String selectedOption = scanner.nextLine().toLowerCase();
        String option = selectedOption.trim();

        if (option.equals("m"))
        {
            return "feed money";
        }
        else if (option.equals("s"))
        {
            return "select item";
        }
        else if (option.equals("f"))
        {
            return "finish transaction";
        }
        else if (option.equals("q")) {
            return "q";
        }
        else
        {
        return null;
    }
    }




}
