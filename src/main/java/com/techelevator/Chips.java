package com.techelevator;

public class Chips extends Purchasable{

    public Chips(double price, String itemName) {
        super(price, itemName);
        this.setPurchaseMessage("Crunch Crunch, Yum!");
    }
}
