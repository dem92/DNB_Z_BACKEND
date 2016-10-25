package no.westerdals.PJ3100g15;

import java.util.ArrayList;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public class Customer extends User {
    private ArrayList<Account> accounts;

    public Customer(String id, String firstName, String lastName, String addressLine1, String addressLine2, int postalCode, String eMailAddress, int phoneNumber){
        super(id, firstName, lastName, addressLine1, addressLine2, postalCode, eMailAddress, phoneNumber);
        accounts = new ArrayList<Account>();
    }

    public boolean addAccount(Account account){
        if (account == null) return false;

        accounts.add(account);
        return  true;
    }


    // Get and set.
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

}
