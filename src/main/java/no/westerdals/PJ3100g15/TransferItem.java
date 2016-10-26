package no.westerdals.PJ3100g15;

import java.util.Date;

/**
 * Created by Eva Dahlo on 26/10/2016.
 */
public class TransferItem extends LogItem {
    private final int fromAccount;
    private final int toAccount;
    private final int kroner;
    private final int oere;

    public TransferItem(int logCount, int customerID, int fromAccount, int toAccount, int kroner, int oere){
        super(logCount, customerID);
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.kroner = kroner;
        this.oere = oere;
    }

    public Date getTime() {
        return time;
    }

    public String getTimeString() {
        return timeString;
    }

    public String getId() {
        return id;
    }

    public int getFromAccount() {
        return fromAccount;
    }

    public int getToAccount() {
        return toAccount;
    }

    public int getKroner() {
        return kroner;
    }

    public int getOere() {
        return oere;
    }
}
