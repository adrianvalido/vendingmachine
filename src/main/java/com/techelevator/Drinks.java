package com.techelevator;

public class Drinks extends Purchasable {

    public Drinks(double price, String itemName) {
        super(price, itemName);
        this.setPurchaseMessage("Glug Glug, Yum!");
    }
}
