package DataModel;

import Util.TransactionList;

/**
 * ACIT 2515 Final Test
 *
 * @author Daniele Lisi - A00978006 Set A
 * @date 2017-04-17
 */
public class DataTransactions {

    // holds an intance of TransactionList used to share data between controllers
    private static TransactionList transactionList = new TransactionList();

    public static TransactionList getTransactionList() {
        return transactionList;
    }
}
