package no.westerdals.pj3100g15.users;

import no.westerdals.pj3100g15.BankAccount;

import java.util.ArrayList;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public class Customer extends User {
    private ArrayList<BankAccount> accounts;
    private int logCount;
    private int score;


    public Customer(String id, String firstName, String lastName, String addressLine1,/* String addressLine2, */ int postalCode, String eMailAddress, int phoneNumber, int score){
        super(id, firstName, lastName, addressLine1, /* addressLine2, */ postalCode, eMailAddress, phoneNumber);
        accounts = new ArrayList<BankAccount>();
        logCount = 0;
        this.score = score;
    }

    public boolean addAccount(BankAccount account){
        if (account == null) return false;

        accounts.add(account);
        return  true;
    }



    // Get and set.
    public ArrayList<BankAccount> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<BankAccount> accounts) {
        this.accounts = accounts;
    }

}
