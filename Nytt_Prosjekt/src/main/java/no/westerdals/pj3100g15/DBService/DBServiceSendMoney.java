package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.Account;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.math.BigInteger;
import java.sql.SQLException;

public class DBServiceSendMoney {

    //checks if input is negative or not, returns true if compareTo() returns -1
    public static boolean validateInput(BigInteger input){
        if(input.compareTo(BigInteger.ZERO) >= 0){
            return true;
        }
        return false;
    }


    public static boolean sendMoney(String accountNumber, String accountNumber2, BigInteger kroner, int oere) {
        DBServiceConnection.makeConnection();
        Account sending = DBServiceAccount.getAccount(accountNumber);
        Account recieving = DBServiceAccount.getAccount(accountNumber2);
        boolean hasMoney = false;

        if (oere > 99 || oere < 0) {
            return false;
        }

        if (sending.getOere() < oere) {
            sending.setKroner(sending.getKroner().subtract((BigInteger.ONE)));
            sending.setOere(sending.getOere() + 100);
        }

        if (validateInput(kroner) &&  sending.getKroner().subtract(kroner).compareTo(BigInteger.ZERO) >= 0) {
            hasMoney = true;
        }
        if (hasMoney) {
            sending.setOere(sending.getOere() - oere);
            sending.setKroner(sending.getKroner().subtract(kroner));
            recieving.setKroner(recieving.getKroner().add(kroner));
            recieving.setOere(recieving.getOere() + oere);
            if (recieving.getOere() > 99) {
                recieving.setKroner(recieving.getKroner().add(BigInteger.ONE));
                recieving.setOere(recieving.getOere() - 100);
            }
            try {
                Dao<Account, String> accountDao = DaoManager.createDao(DBServiceConnection.connectionSource, Account.class);
                accountDao.update(sending);
                accountDao.update(recieving);
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
