package Util;

import java.util.ArrayList;

/**
 * ACIT 2515 Final Test
 *
 * @author Daniele Lisi - A00978006 Set A
 * @date 2017-04-17
 */
public class TransactionList {

    private ArrayList<Transaction> itemList = new ArrayList<>();

    public ArrayList<Transaction> getItemList() {
        return itemList;
    }
    public void setItemList(ArrayList<Transaction> itemList) {
        this.itemList = itemList;
    }
}
