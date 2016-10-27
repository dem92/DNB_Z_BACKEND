package no.westerdals.PJ3100g15;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public class Customer extends User implements Log{
    private ArrayList<Account> accounts;
    private int logCount;
    private ArrayList<LogItem> log;

    public Customer(int id, String firstName, String lastName, String addressLine1, String addressLine2, int postalCode, String eMailAddress, int phoneNumber){
        super(id, firstName, lastName, addressLine1, addressLine2, postalCode, eMailAddress, phoneNumber);
        accounts = new ArrayList<Account>();
        logCount = 0;
        log = new ArrayList<LogItem>();
    }

    public boolean addAccount(Account account){
        if (account == null) return false;

        accounts.add(account);
        return  true;
    }

    public boolean addLogItem(LogItem item){
        return log.add(item);
    }

    public void logTransfer(int fromAccount, int toAccount, int kroner, int oere){
        logCount++;
        TransferItem item = new TransferItem(logCount, super.ID, fromAccount, toAccount, kroner, oere);
        log.add(item);
    }

    public void logDeposit(){

    }

    public void logWithdrawal(){

    }


    // Get and set.
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

}
