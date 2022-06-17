package com.techelevator;

import java.util.Currency;

public abstract class Purchasable {
    private final int MAX_STOCK = 5;
    private int stock;

    private double price;
    private String itemName;
    private String purchaseMessage;

    public Purchasable(double price, String itemName){
        this.price = price;
        this.itemName = itemName;
        this.stock = MAX_STOCK;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getPurchaseMessage() {
        return purchaseMessage;
    }

    public void setPurchaseMessage(String purchaseMessage) {
        this.purchaseMessage = purchaseMessage;
    }



}
