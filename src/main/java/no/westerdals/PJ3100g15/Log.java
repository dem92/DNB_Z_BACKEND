package no.westerdals.PJ3100g15;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public interface Log {
    void logTransfer(int fromAccount, int toAccount, int kroner, int oere);

    void logWithdrawal();

    void logDeposit();
}
