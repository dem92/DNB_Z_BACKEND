package no.westerdals.PJ3100g15;


import no.westerdals.PJ3100g15.users.Customer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.crypto.Data;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public class Account {
    private final int accountNumber;
    private final Customer customer;

    private final Database database = new Database();

    /*public Account() {
        foedselsnummer = 0;
        accountType = null;
    }*/

    public Account(int accountNumber, Customer customer) {
        this.accountNumber = accountNumber;
        this.customer = customer;
    }

    /**
     * Add amount to account balance.
     *
     * @param kroner
     * @param oere
     * @return
     */
    public boolean addAmount(int kroner, int oere) {
        int existingKroner = getKroner();
        int existingOere = getOere();

        if ((kroner < 0 || oere < 0) || (kroner == 0 && oere == 0))
            return false;

        existingOere += oere;
        if (existingOere >= 100) {
            existingOere -= 100;
            existingKroner++;
        }
        setKroner(existingKroner);
        setOere(existingOere);
        return true;
    }

    /**
     * Subtract amount from account balance.
     *
     * @param kroner
     * @param oere
     * @return
     */
    public boolean subtractAmount(int kroner, int oere) { // TODO exception handling
        int existingKroner = getKroner();
        int existingOere = getOere();

        if ((kroner < 0 || oere < 0) || (kroner == 0 && oere == 0))
            return false;

        existingOere -= oere;
        if (existingOere < 0) {
            existingOere += 100;
            existingKroner--;
        }

        setKroner(existingKroner);
        setOere(existingOere);
        return true;
    }

    public void logTransfer(int fromAccount, int toAccount, int kroner, int oere) {
        customer.logTransfer(fromAccount, toAccount, kroner, oere);
    }

    // Get and set.
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
<<<<<<< HEAD
        String query = "SELECT Kontotype FROM Bankkonto WHERE Kundenummer = " + ID;
        String accountType = database.returnString(query);
=======
        return accountType;
    }

    public double getInterestRate() {
        // TODO: Daniel, se her!!!
<<<<<<< HEAD
        String query = "SELECT Rente FROM Bankkonto WHERE Kundenummer = " + ID;
        double interestRate = database.returnDouble(query);
=======

        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        // TODO: Daniel, se her!!!
        String query = "Update Bankkonto SET Rente = " + interestRate + " WHERE Kontonummer = " + accountNumber;
    }

    public int getKroner() {
        // TODO: Daniel, se her!!!
        String query = "SELECT Kroner FROM Bankkonto WHERE Kontonummer = " + accountNumber;
        int kroner = 0; // = resultatet av spørringen.

        return kroner;
    }

    public void setKroner(int kroner) {
        // TODO: Daniel, se her!!!
        String query = "Update Bankkonto SET Kroner = " + kroner + " WHERE Kontonummer = " + accountNumber;
    }

    public int getOere() {
        // TODO: Daniel, se her!!!
        String query = "SELECT Oere FROM Bankkonto WHERE Kontonummer = " + accountNumber;
        int oere = 0; // = resultatet av spørringen.

        return oere;
    }

    public void setOere(int oere) {
        // TODO: Daniel, se her!!!
        String query = "Update Bankkonto SET Oere = " + oere + " WHERE Kontonummer = " + accountNumber;
    }
}
