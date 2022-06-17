package com.techelevator;

public class Gum extends Purchasable{

    public Gum(double price, String itemName) {
        super(price, itemName);
        this.setPurchaseMessage("Chew Chew, Yum!");
    }
}
