package com.techelevator;

import com.techelevator.view.Menu;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {

	private final double QUARTER_VALUE = 0.25;
	private final double DIME_VALUE = 0.10;
	private final double NICKEL_VALUE = 0.05;
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String PURCHASE_MENU_OPTION_FEED = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED, PURCHASE_MENU_OPTION_SELECT, PURCHASE_MENU_OPTION_FINISH };
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private Log logger = new Log();
	private double wallet = 0.00;

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}

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
				String[] item = thisLine.split("\\|",-1);
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
				}
			}
		}catch(FileNotFoundException e){
			System.err.println("Inventory file not found.");
		}
	}

	public void feedMoney() {
		int money;
		Scanner input = new Scanner(System.in);
		System.out.print("Please print a whole dollar amount: ");
		String userInput = input.nextLine();

		try {
			money = Integer.parseInt(userInput);
			if (money < 0) {
				System.out.println("Please enter a whole dollar amount.");

			} else {
				logger.logFeed(money,wallet);
				wallet += money;
			}

		} catch(NumberFormatException e){
			System.out.println("Please enter a whole dollar amount.");
		}

		return;
	}

	public void selectProduct(){
		displayItems();
		Scanner input = new Scanner(System.in);
		String walletCheck = "\nCurrent Money Provided: $";
		System.out.print(walletCheck);
		System.out.printf("%3.2f", wallet);
		System.out.print("\nEnter a product code: ");
		String userInput = input.nextLine().toUpperCase();
		if(!inventory.containsKey(userInput)){
			System.out.println("Item does not exist.");
			return;
		} else if(inventory.get(userInput).getStock() == 0) {
			System.out.println("Item is sold out.");
			return;
		} else if(wallet < inventory.get(userInput).getPrice()){
			System.out.println("You do not have enough money for this item.");
		} else{
			logger.logTransactions(userInput,inventory.get(userInput),wallet);
			inventory.get(userInput).setStock(inventory.get(userInput).getStock() - 1);
			System.out.print(inventory.get(userInput).getItemName()+"|$");
			System.out.printf("%3.2f", inventory.get(userInput).getPrice());
			System.out.print("|");
			System.out.printf("%3.2f", wallet - inventory.get(userInput).getPrice());
			System.out.println("\n" + inventory.get(userInput).getPurchaseMessage());
			wallet -= inventory.get(userInput).getPrice();
			return;

		}

	}


	public void displayItems(){
		for(Map.Entry<String, Purchasable> items : inventory.entrySet()) {
			System.out.print(items.getKey()+"|");
			System.out.print(items.getValue().getItemName()+"|$");
			System.out.printf("%3.2f", items.getValue().getPrice());
			if (items.getValue().getStock() > 0) {
				System.out.println("|"+items.getValue().getStock());
			} else {
				System.out.println("|SOLD OUT");
			}
		} return;

	}

	public void finishTransaction(){
		logger.logChange(wallet);
		int quarterAmount = (int) Math.floor(wallet/QUARTER_VALUE);
		wallet -= quarterAmount * QUARTER_VALUE;
		int dimeAmount = (int) Math.floor(wallet/DIME_VALUE);
		wallet -= dimeAmount * DIME_VALUE;
		double nickelAmount = Math.floor(wallet/NICKEL_VALUE);
		wallet -= nickelAmount * NICKEL_VALUE;
		System.out.println("your change is " + quarterAmount + " quarter(s), " + dimeAmount + " dime(s), and " + (int)nickelAmount + " nickel(s)." );
	}

	public void displayPurchaseMenu() {

		while (true) {
			String walletCheck = "\nCurrent Money Provided: $";
			System.out.print(walletCheck);
			System.out.printf("%3.2f", wallet);
			String purchaseChoice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FEED)) {
				feedMoney();
			} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_SELECT)) {
				selectProduct();
			} else if (purchaseChoice.equals(PURCHASE_MENU_OPTION_FINISH)) {
				finishTransaction();
				return;
			}
		}
	}


	public void run() {
		while (true) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayItems();

			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				// do purchase
				displayPurchaseMenu();

			} else if (choice.equals((MAIN_MENU_OPTION_EXIT))) {
				System.out.println("Goodbye!");
				return;
			}
		}
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.stock();
		cli.run();
	}
}
