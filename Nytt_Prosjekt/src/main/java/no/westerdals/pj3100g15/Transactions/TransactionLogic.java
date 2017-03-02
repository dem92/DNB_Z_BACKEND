package no.westerdals.pj3100g15.Transactions;

import no.westerdals.pj3100g15.ORM.Account;

import java.math.BigInteger;

public class TransactionLogic {

    public void transferPayment(Account sending, Account receiving, BigInteger amount) {
        //TODO Denne burde også oppdatere øre, det gjør den ikke nå.
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
