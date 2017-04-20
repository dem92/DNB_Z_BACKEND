package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.LoggedTransaction;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class DBServiceLoggedTransaction {
    public static List<LoggedTransaction> getAllLoggedTransactionsFromAccount(String accountNumber) {
        DBServiceConnection.makeConnection();
        try {
            Dao<LoggedTransaction, String> transactionDao = DaoManager.createDao(DBServiceConnection.connectionSource, LoggedTransaction.class);
            List<LoggedTransaction> transactions = transactionDao.queryForEq("Avsenderkonto", accountNumber);
            transactions = sortTransactions(transactions);
            transactions.addAll(transactionDao.queryForEq("Mottakerkonto", accountNumber));
            return transactions;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    public static List<LoggedTransaction> sortTransactions(List<LoggedTransaction> transactions){
        for(int i = 0; i < transactions.size(); i++){
            for(int j = 0; j < transactions.size(); j++){
                if(transactions.get(i).getTimestamp() < transactions.get(j).getTimestamp()){
                    LoggedTransaction temp = transactions.get(i);
                    transactions.set(i, transactions.get(j));
                    transactions.set(j, temp);
                }
            }
        }


        return transactions;
    }

    public static boolean logTransfer(String accountNumber, String accountNumber2, BigInteger kroner, int oere, String message) {
        DBServiceConnection.makeConnection();
        LoggedTransaction loggedTransaction = new LoggedTransaction();
        loggedTransaction.setSendingAccount(accountNumber);
        loggedTransaction.setRecievingAccount(accountNumber2);
        loggedTransaction.setKroner(kroner);
        loggedTransaction.setOere(oere);
        loggedTransaction.setMessage_kid(message);

        loggedTransaction.setTimestamp(System.currentTimeMillis() / 1000L);

        if (DBServiceAccount.getAccount(accountNumber).getCustomerNumber() == DBServiceAccount.getAccount(accountNumber2).getCustomerNumber()) {
            loggedTransaction.setTransactionType("transfer");
        } else if (!(DBServiceAccount.getAccount(accountNumber).getCustomerNumber() == DBServiceAccount.getAccount(accountNumber2).getCustomerNumber()) && (accountNumber != null || accountNumber2 != null)) {
            loggedTransaction.setTransactionType("payment");
        } else if (accountNumber == null) {
            loggedTransaction.setTransactionType("payment");
        } else if (accountNumber2 == null) {
            loggedTransaction.setTransactionType("card");
        }

        if (accountNumber != null){
            loggedTransaction.setSenderID(DBServiceAccount.getAccount(accountNumber).getCustomerNumber());
        }

        if (accountNumber2 != null){
            loggedTransaction.setReceiverID(DBServiceAccount.getAccount(accountNumber2).getCustomerNumber());
        }

        try {
            Dao<LoggedTransaction, Integer> loggedTransactionIntegerDao = DaoManager.createDao(DBServiceConnection.connectionSource, LoggedTransaction.class);
            loggedTransactionIntegerDao.create(loggedTransaction);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }
}
