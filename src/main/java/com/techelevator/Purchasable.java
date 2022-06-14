package com.techelevator;

import java.util.Currency;

public abstract class Purchasable {
    private int stock = 5;
    private int soldStock;
    private double price;
    private String itemName;
    private String slotName;

    public Purchasable(double price, String itemName, String slotName){
        this.price = price;
        this.itemName = itemName;
        this.slotName = slotName;
    }

    public int getSoldStock() {
        return soldStock;
    }

    public void setSoldStock(int soldStock) {
        this.soldStock = soldStock;
    }

    private String itemType;
    private String purchaseMessage;


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

    public String getSlotName() {
        return slotName;
    }

    public void setSlotName(String slotName) {
        this.slotName = slotName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getPurchaseMessage() {
        return purchaseMessage;
    }

    public void setPurchaseMessage(String purchaseMessage) {
        this.purchaseMessage = purchaseMessage;
    }



}
