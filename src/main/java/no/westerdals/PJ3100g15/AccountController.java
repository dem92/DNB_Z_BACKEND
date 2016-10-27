package no.westerdals.PJ3100g15;

import java.util.HashMap;

/**
 * Created by Eva Dahlo on 27/10/2016.
 */
public class AccountController {
    private HashMap<Integer, Account> accounts;

    public AccountController(){
        accounts = new HashMap<Integer, Account>();
    }

    public boolean addAccount(int accountID, Account account){
        if (accounts.containsKey(accountID))
            return false;

        accounts.put(accountID, account);
        return true;
    }

    public boolean containsAccount(int accountID){
        if (accounts.containsKey(accountID))
            return true;
        return false;
    }

    public int getNumberOfAccounts(){
        return accounts.size();
    }
}
