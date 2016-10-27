package no.westerdals.PJ3100g15;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public class Account {
    private final int ID;

    /*public Account() {
        ID = 0;
        accountType = null;
    }*/

    public Account(int id) {
        this.ID = id;
    }

    /**
     * Add amount to account balance.
     *
     * @param kroner
     * @param oere
     * @return
     */
    public boolean addAmount(int kroner, int oere){
        int existingKroner = getKroner();
        int existingOere = getOere();

        if ((kroner < 0 || oere < 0) || (kroner == 0 && oere == 0))
            return false;

        existingOere += oere;
        if (existingOere >= 100){
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
    public boolean subtractAmount(int kroner, int oere){ // TODO exception handling
        int existingKroner = getKroner();
        int existingOere = getOere();

        if ((kroner < 0 || oere < 0) || (kroner == 0 && oere == 0))
            return false;

        existingOere -= oere;
        if (existingOere < 0){
            existingOere += 100;
            existingKroner--;
        }

        setKroner(existingKroner);
        setOere(existingOere);
        return true;
    }

    // Get and set.
    public int getID() {
        return ID;
    }

    public String getAccountType() {
        // TODO: Daniel, se her!!!
        String query = "SELECT Kontotype FROM Bankkonto WHERE Kundenummer = " + ID;
        String accountType = ""; // = resultatet av spørringen.

        return accountType;
    }

    public double getInterestRate() {
        // TODO: Daniel, se her!!!
        String query = "SELECT Rente FROM Bankkonto WHERE Kundenummer = " + ID;
        double interestRate = 0; // = resultatet av spørringen.

        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        // TODO: Daniel, se her!!!
        String query = "Update Bankkonto SET Rente = " + interestRate + " WHERE Kundenummer = " + ID;
    }

    public int getKroner() {
        // TODO: Daniel, se her!!!
        String query = "SELECT Kroner FROM Bankkonto WHERE Kundenummer = " + ID;
        int kroner = 0; // = resultatet av spørringen.

        return kroner;
    }

    public void setKroner(int kroner) {
        // TODO: Daniel, se her!!!
        String query = "Update Bankkonto SET Kroner = " + kroner + " WHERE Kundenummer = " + ID;
    }

    public int getOere() {
        // TODO: Daniel, se her!!!
        String query = "SELECT Oere FROM Bankkonto WHERE Kundenummer = " + ID;
        int oere = 0; // = resultatet av spørringen.

        return oere;
    }

    public void setOere(int oere) {
        // TODO: Daniel, se her!!!
        String query = "Update Bankkonto SET Oere = " + oere + " WHERE Kundenummer = " + ID;
    }
}
