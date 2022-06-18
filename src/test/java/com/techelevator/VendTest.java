package com.techelevator;

import com.techelevator.view.Menu;
import org.junit.Test;

public class VendTest {

    VendingMachineCLI test = new VendingMachineCLI(new Menu(System.in,System.out));

    @Test
    public void inputted_money_is_added_to_wallet(){
        test.feedMoney();
    }
}
