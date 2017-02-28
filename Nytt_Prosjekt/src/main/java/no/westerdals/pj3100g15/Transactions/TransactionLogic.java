package no.westerdals.pj3100g15.Transactions;

import no.westerdals.pj3100g15.ORM.Account;

import java.math.BigInteger;

/**
 * Created by Henrik on 28.02.2017.
 */
public class TransactionLogic {

    public void transferPayment(Account sending, Account receiving, BigInteger amount) {
        //både kroner og øre-feltet må oppdateres for begge kontoene.
        // Benytter subtractAmount og addAmount-metodene i Account-klassen
        if (controlAccounts(sending, amount)) {
            sending.setKroner(sending.getKroner().subtract(amount));
            receiving.setKroner(receiving.getKroner().add(amount));
        }
    }

    public boolean controlAccounts(Account sending, BigInteger amount) {
        if (amount.compareTo(sending.getKroner()) >= 0) {
            return true;
        }
        return false;
    }
}
