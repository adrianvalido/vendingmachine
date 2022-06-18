package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Log {

    public Log(){

    }


    File logFile = new File("C:\\Users\\adria\\OneDrive\\Desktop\\MERIT\\capstone\\capstone-1\\src\\main\\resources\\Log.txt");

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/uuuu hh:mm:ss a");
    LocalDateTime now = LocalDateTime.now();


    public String datetime(){
        String currentDateTime = dtf.format(now);
        return currentDateTime;
    }

    public void logTransactions(String userInput, Purchasable food, double wallet){
        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(logFile, true))) {
            dataOutput.print(">"+ datetime() + " " + food.getItemName() + " " + userInput + " ");
            dataOutput.printf("%3.2f", wallet);
            dataOutput.print(" ");
            dataOutput.printf("%3.2f%n", wallet - food.getPrice());
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }

    public void logFeed(double amount, double wallet){
        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(logFile, true))) {
            dataOutput.print(">"+ datetime() + " " + "FEED MONEY: ");
            dataOutput.printf("%3.2f", wallet);
            dataOutput.print(" ");
            dataOutput.printf("%3.2f%n", wallet + amount);
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
    public void logChange(double wallet){
        try (PrintWriter dataOutput = new PrintWriter(new FileOutputStream(logFile, true))) {
            dataOutput.print(">"+ datetime() + " " + "GIVE CHANGE: ");
            dataOutput.printf("%3.2f", wallet);
            dataOutput.println(" 0.00");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }
    }
}
