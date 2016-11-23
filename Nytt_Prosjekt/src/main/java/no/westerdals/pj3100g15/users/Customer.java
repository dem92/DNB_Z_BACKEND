package no.westerdals.pj3100g15.users;

import no.westerdals.pj3100g15.BankAccount;
import no.westerdals.pj3100g15.logging.Log;
import no.westerdals.pj3100g15.logging.LogItem;
import no.westerdals.pj3100g15.logging.TransferItem;

import java.util.ArrayList;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public class Customer extends User implements Log {
    private ArrayList<BankAccount> accounts;
    private int logCount;
    private int score;
    private ArrayList<LogItem> log;

    public Customer(String id, String firstName, String lastName, String addressLine1,/* String addressLine2, */ int postalCode, String eMailAddress, int phoneNumber, int score){
        super(id, firstName, lastName, addressLine1, /* addressLine2, */ postalCode, eMailAddress, phoneNumber);
        accounts = new ArrayList<BankAccount>();
        logCount = 0;
        log = new ArrayList<LogItem>();
        this.score = score;
    }

    public boolean addAccount(BankAccount account){
        if (account == null) return false;

        accounts.add(account);
        return  true;
    }

    public boolean addLogItem(LogItem item){
        return log.add(item);
    }

    public void logTransfer(int fromAccount, int toAccount, int kroner, int oere){
        logCount++;
        TransferItem item = new TransferItem(logCount, super.foedselsnummer, fromAccount, toAccount, kroner, oere);
        log.add(item);
    }

    public void logDeposit(){

    }

    public void logWithdrawal(){

    }


    // Get and set.
    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<BankAccount> accounts) {
        this.accounts = accounts;
    }

}
