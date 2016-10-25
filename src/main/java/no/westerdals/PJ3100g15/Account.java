package no.westerdals.PJ3100g15;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public class Account {
    private final int ID;
    private final AccountType accountType;
    private double interestRate;
    private int kroner;
    private int oere;

    public Account() {
        ID = 0;
        accountType = null;
    }

    public Account(int id, AccountType accountType, double interestRate) {
        this.ID = id;
        this.accountType = accountType;
        this.interestRate = interestRate;
        kroner = 0;
        oere = 0;
    }

    /**
     * Add amount to account balance.
     *
     * @param kroner
     * @param oere
     * @return
     */
    public boolean addAmount(int kroner, int oere){
        if ((kroner < 0 || oere < 0) || (kroner == 0 && oere == 0)) return false;

        this.oere += oere;
        if (this.oere >= 100){
            this.oere -= 100;
            this.kroner++;
        }
        this.kroner += kroner;
        return true;
    }

    /**
     * Subtract amount from account balance.
     *
     * @param kroner
     * @param oere
     * @return
     */
    public boolean subtractAmount(int kroner, int oere){ // TODO exception handling
        if ((kroner < 0 || oere < 0) || (kroner == 0 && oere == 0)) return false;

        this.oere -= oere;
        if (this.oere < 0){
            this.oere += 100;
            this.kroner--;
        }

        this.kroner -= kroner;
        return true;
    }

    // Get and set.
    public int getID() {
        return ID;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getKroner() {
        return kroner;
    }

    public void setKroner(int kroner) {
        this.kroner = kroner;
    }

    public int getOere() {
        return oere;
    }

    public void setOere(int oere) {
        this.oere = oere;
    }
}
