/*
 * Copyright (c) 2017. Group 15, PJ-3100, Westerdals Oslo ACT
 */

package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.Account;
import no.westerdals.pj3100g15.ORM.SavingsTargets;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;
import no.westerdals.pj3100g15.ValidateData.ValidateInput;

import java.math.BigInteger;
import java.sql.SQLException;

import static no.westerdals.pj3100g15.DBService.DBServiceAccount.*;
import static no.westerdals.pj3100g15.DBService.DBServiceSavingsTargets.*;

public class DBServiceSendMoney {
    /**
     * Sends money from a savingstarget to an account.
     *
     * @param accountNumber  is used to get the specified account from the database.
     * @param savingTargetId is used to get the specified savingstarget from the database.
     * @param kroner         the amount of kroner that is being transfered.
     * @param oere           the amount of oere that is being transfered.
     * @return boolean
     */
    public static boolean sendMoneyFromSavingsTargetToAccount(String accountNumber, int savingTargetId, BigInteger kroner, int oere) {
        DBServiceConnection.makeConnection();
        SavingsTargets savingsTarget = getSavingsTarget(savingTargetId);
        Account account = getAccount(accountNumber);

        if (savingsTarget == null || account == null) {
            return false;
        }

        boolean hasMoney = false;

        if (ValidateInput.validateInput(kroner, oere) && savingsTarget.getSavedKroner().subtract(kroner).compareTo(BigInteger.ZERO) >= 0) {
            hasMoney = true;
        }

        if (hasMoney) {
            if (lessOereOnSavingsTargetThanSentOere(savingsTarget, oere)) {
                subtractOneKroneAndAdd100OereToSavingsTargets(savingsTarget);
            }

            //Subtracts from savingstarget and adds to account
            savingsTarget = subtractFromTarget(savingsTarget, kroner, oere);
            account = addToAccount(account, kroner, oere);

            //Checks if any of the objects is null to avoid nullpointer exception
            if (savingsTarget != null || account != null) {

                //Completes the transaction if updating both the account and the savingstarget is true
                if (updateSavingsTarget(savingsTarget) && updateAccount(account)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Sends money from account to savingstarget. If the savingstarget is done, the savingstarget marks itself as done and transfers the
     * overflowing money back to the account.
     *
     * @param accountNumber   used to get the specified accountobject in the database.
     * @param savingsTargetId used to get the specified svingstarget in the database.
     * @param kroner          the amount of kroner that is being transfered.
     * @param oere            the amount of oere that is being transfered.
     * @return boolean
     */
    public static boolean sendMoneyFromAccountToSavingsTarget(String accountNumber, int savingsTargetId, BigInteger kroner, int oere) {
        DBServiceConnection.makeConnection();
        SavingsTargets savingsTarget = getSavingsTarget(savingsTargetId);
        Account account = getAccount(accountNumber);
        boolean hasMoney = false;

        if (savingsTarget == null || account == null) {
            return false;
        }

        if (ValidateInput.validateInput(kroner, oere) && account.getKroner().subtract(kroner).compareTo(BigInteger.ZERO) >= 0) {
            hasMoney = true;
        }

        if (hasMoney) {

            if (lessOereOnAccountThanSentOere(account, oere)) {
                account = subtractOneKroneAndAdd100OereToAccount(account);
            }

            //Subtracts from account and adds to savingstarget
            account = subtractFromAccount(account, kroner, oere);
            savingsTarget = addToTarget(savingsTarget, kroner, oere);

            //Checks if any of the objects is null to avoid nullpointer exception
            if (savingsTarget != null || account != null) {

                //Completes the transaction if updating both the account and the savingstarget is true
                if (updateSavingsTarget(savingsTarget) && updateAccount(account)) {

                    //Checks the savingstargets status
                    if (checkIfTargetIsDone(savingsTarget)) {

                        //Sets the savingstarget to "Done" and checks if the transaction to the database has gone well
                        if (targetIsDone(savingsTarget)) {

                            //Gets the overflowing money from the savingstarget
                            BigInteger overflowingKroner = getOverflowingKronerOfDoneSavingsTarget(savingsTarget);
                            int overFlowingOere = getOverflowingOereOfDoneSavingsTarget(savingsTarget);

                            //Sends the money back to the the account it was sent from
                            sendMoneyFromSavingsTargetToAccount(accountNumber, savingsTargetId, overflowingKroner, overFlowingOere);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Is used to send money between accounts in the database.
     *
     * @param accountNumber  is used to get the account with the specified accountnumber from the database.
     *                       The money will be transfered FROM this account(sending).
     * @param accountNumber2 is used to get the account with the specified accountnumber from the database.
     *                       The money will be transfered TO this account(receiving).
     * @param kroner         the amount of money being sent.
     * @param oere           the amount of oere being sent.
     * @return boolean
     */
    public static boolean sendMoneyBetweenAccounts(String accountNumber, String accountNumber2, BigInteger kroner, int oere) {
        DBServiceConnection.makeConnection();
        Account sending = DBServiceAccount.getAccount(accountNumber);
        Account receiving = DBServiceAccount.getAccount(accountNumber2);
        boolean hasMoney = false;

        if (sending == null || receiving == null) {
            return false;
        }

        if (sending.getAccountNumber().equals(receiving.getAccountNumber())) {
            return false;
        }

        if (oere > 99 || oere < 0) {
            return false;
        }

        if (sending.getOere() < oere) {
            sending.setKroner(sending.getKroner().subtract(BigInteger.ONE));
            sending.setOere(sending.getOere() + 100);
        }

        if (ValidateInput.validateInput(kroner, oere) && sending.getKroner().subtract(kroner).compareTo(BigInteger.ZERO) >= 0) {
            hasMoney = true;
        }
        if (hasMoney) {
            sending.setOere(sending.getOere() - oere);
            sending.setKroner(sending.getKroner().subtract(kroner));
            receiving.setKroner(receiving.getKroner().add(kroner));
            receiving.setOere(receiving.getOere() + oere);
            if (receiving.getOere() > 99) {
                receiving.setKroner(receiving.getKroner().add(BigInteger.ONE));
                receiving.setOere(receiving.getOere() - 100);
            }
            try {
                Dao<Account, String> accountDao = DaoManager.createDao(DBServiceConnection.connectionSource, Account.class);
                accountDao.update(sending);
                accountDao.update(receiving);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                WriteLogg.writeLogg(e);
            }
        } else {
            sending.setKroner(sending.getKroner().add((BigInteger.ONE)));
            sending.setOere(sending.getOere() - 100);
        }
        return false;
    }


}
