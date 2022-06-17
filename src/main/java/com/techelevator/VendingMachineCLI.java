package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE };
	private double balance = 0;

	private Menu menu;

	Map<String, Purchasable> inventory = new HashMap<String, Purchasable>();

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public void stock(){
		File itemsInStock = new File("vendingmachine.csv");
		try(Scanner fileInput = new Scanner(itemsInStock)){
			while(fileInput.hasNextLine()){
				String thisLine = fileInput.nextLine();
				String[] item = thisLine.split("|");
				double price = Double.parseDouble(item[2]);
				switch(item[3]){
					case "Drink" :
						inventory.put(item[0], new Drinks(price, item[1]));
					case "Chip" :
						inventory.put(item[0], new Chips(price, item[1]));
					case "Candy" :
						inventory.put(item[0], new Candy(price, item[1]));
					case "Gum" :
						inventory.put(item[0], new Gum(price, item[1]));
					default :
						System.err.println("Invalid entry");
				}
			}
		}catch(FileNotFoundException e){
			System.err.println("Inventory file not found.");
		}
	}

	public String purchase(){
	}

	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
			}
		}
	}

	public static void main(String[] args) {


		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
