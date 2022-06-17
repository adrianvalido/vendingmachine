package com.techelevator;

public class Candy extends Purchasable {

    public Candy(double price, String itemName) {
        super(price, itemName);
        this.setPurchaseMessage("Munch Munch, Yum!");
    }
}