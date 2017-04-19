package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.Account;
import no.westerdals.pj3100g15.ORM.SavingsTargets;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.math.BigInteger;
import java.sql.SQLException;

import static no.westerdals.pj3100g15.DBService.DBServiceAccount.*;
import static no.westerdals.pj3100g15.DBService.DBServiceSavingsTargets.*;

public class DBServiceSendMoney {


    //TODO legge validateInput og tilhørende metoder inn i en egen klasse

    //Checks if oere is negative or more than 100
    public static boolean validateOere(int oere){
        if (oere < 99 || oere >= 0) {
            return true;
        }
        return false;
    }

    //Checks if the variable kroner is negative
    public static boolean validateKroner(BigInteger kroner){
        if(kroner.compareTo(BigInteger.ZERO) >= 0){
            return true;
        }
        return false;
    }

    //checks if input is negative or not, returns true if compareTo() returns -1
    public static boolean validateInput(BigInteger kroner, int oere){
        if(validateKroner(kroner) && validateOere(oere)){
            return true;
        }
        return false;
    }

    public static boolean sendMoneyFromSavingsTargetToAccount(String accountNumber, int savingTargetId, BigInteger kroner, int oere){
        DBServiceConnection.makeConnection();
        SavingsTargets savingsTarget = getSavingsTarget(savingTargetId);
        Account account = getAccount(accountNumber);

        //Subtracts from savingstarget and adds to account
        savingsTarget = subtractFromTarget(savingsTarget, kroner, oere);
        account = addToAccount(account, kroner, oere);

        //Checks if any of the objects is null to avoid nullpointer exception
        if(savingsTarget != null || account != null){

            //Completes the transaction if updating both the account and the savingstarget is true
            if(updateSavingsTarget(savingsTarget) && updateAccount(account)){
                return true;
            }
        }
        return false;
    }

    public static boolean sendMoneyFromAccountToSavingsTarget(String accountNumber, int savingsTargetId, BigInteger kroner, int oere) {
        DBServiceConnection.makeConnection();
        SavingsTargets savingsTarget = getSavingsTarget(savingsTargetId);
        Account account = getAccount(accountNumber);

        //Subtracts from savingstarget and adds to account
        savingsTarget = addToTarget(savingsTarget, kroner, oere);
        account = subtractFromAccount(account, kroner, oere);

        //Checks if any of the objects is null to avoid nullpointer exception
        if(savingsTarget != null || account != null){

            //Completes the transaction if updating both the account and the savingstarget is true
            if(updateSavingsTarget(savingsTarget) && updateAccount(account)){
                return true;
            }
        }
        return false;
    }


    public static boolean sendMoneyBetweenAccounts(String accountNumber, String accountNumber2, BigInteger kroner, int oere) {
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

        if (validateInput(kroner, oere) &&  sending.getKroner().subtract(kroner).compareTo(BigInteger.ZERO) >= 0) {
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
