/*
 * Copyright (c) 2017. Group 15, PJ-3100, Westerdals Oslo ACT
 */

package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.RecurringTransfer;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class DBServiceRecurringTransfer {

    /**
     * Returns all the recurring transfers associated with the account with the specified accountnumber in the database.
     * @param accountNumber is the number needed to retrieve the Account-object from the DB.
     * @return a list with all the RecurringTransfers.
     */
    public static List<RecurringTransfer> getAllRecurringTransfersForAccount(String accountNumber) {
        DBServiceConnection.makeConnection();
        try {
            Dao<RecurringTransfer, Integer> recurringTransfersDao = DaoManager.createDao(DBServiceConnection.connectionSource, RecurringTransfer.class);
            List<RecurringTransfer> recurringTransfers = recurringTransfersDao.queryForEq("Avsenderkonto", accountNumber);
            return recurringTransfers;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Creates a recurringtransfer from an account to another.
     * @param sendingAccount is the accountNumber of the sendingaccount.
     * @param receivingAccount is the accountnumber for the receivingaccount.
     * @param kroner is the amount of kroner being transfered.
     * @param oere is the amount of oere being transfered.
     * @param message is the message associated with the recurring transfer.
     * @param interval determines how often the transfer is going to happend.
     * @param endDate is the date that stops this recurring transfer.
     */
    public static void addRecurringTransfer(String sendingAccount, String receivingAccount, BigInteger kroner, int oere, String message, String interval, long endDate) {
        DBServiceConnection.makeConnection();

        RecurringTransfer recurringTransfer = new RecurringTransfer();
        recurringTransfer.setSendingAccount(sendingAccount);
        recurringTransfer.setReceivingAccount(receivingAccount);
        recurringTransfer.setKroner(kroner);
        recurringTransfer.setOere(oere);
        recurringTransfer.setMessage(message);
        recurringTransfer.setIntervall(interval);
        recurringTransfer.setEndDate(endDate);
        recurringTransfer.setActive(true);
        recurringTransfer.setCustomerId(DBServiceAccount.getAccount(sendingAccount).getCustomerNumber());

        Random random = new Random();
        int i = random.nextInt(10000);
        long nextTransfer = 1499428800 + i * 100;

        recurringTransfer.setNextTransfer(nextTransfer);

        try {
            Dao<RecurringTransfer, Integer> recurringTransfersDao = DaoManager.createDao(DBServiceConnection.connectionSource, RecurringTransfer.class);
            recurringTransfersDao.create(recurringTransfer);

        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
    }
}
