package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.Account;
import no.westerdals.pj3100g15.ORM.SavingsTargets;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import static no.westerdals.pj3100g15.DBService.DBServiceAccount.getAccount;
import static no.westerdals.pj3100g15.DBService.DBServiceAccount.updateAccount;
import static no.westerdals.pj3100g15.DBService.DBServiceSendMoney.validateInput;
import static no.westerdals.pj3100g15.DBService.DBServiceSendMoney.validateKroner;

public class DBServiceSavingsTargets {

    public static boolean deleteSavingsTarget(int savingsTargetId) {
        DBServiceConnection.makeConnection();
        try {
            Dao<SavingsTargets, Integer> savingsTargetsIntegerDao = DaoManager.createDao(DBServiceConnection.connectionSource, SavingsTargets.class);
            SavingsTargets savingsTargets = savingsTargetsIntegerDao.queryForId(savingsTargetId);
            savingsTargetsIntegerDao.delete(savingsTargets);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    public static SavingsTargets getSavingsTarget(int savingsTargetId) {
        DBServiceConnection.makeConnection();
        try {
            Dao<SavingsTargets, Integer> savingsTargetsIntegerDao = DaoManager.createDao(DBServiceConnection.connectionSource, SavingsTargets.class);
            SavingsTargets savingsTargets = savingsTargetsIntegerDao.queryForId(savingsTargetId);
            return savingsTargets;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    public static List<SavingsTargets> getAllSavingsTargetsForUser(int customerId) {
        DBServiceConnection.makeConnection();
        try {
            Dao<SavingsTargets, Integer> savingsTargetsIntegerDao = DaoManager.createDao(DBServiceConnection.connectionSource, SavingsTargets.class);
            return savingsTargetsIntegerDao.queryForEq("KundeID", customerId);
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    public static boolean createSavingsTarget(BigInteger goalKroner, int goalOere, int customerId, String name) {
        DBServiceConnection.makeConnection();
        SavingsTargets savingsTargets = new SavingsTargets();
        savingsTargets.setCustomerId(customerId);
        savingsTargets.setDone(false);
        savingsTargets.setGoalKroner(goalKroner);
        savingsTargets.setGoalOere(goalOere);
        savingsTargets.setSavedKroner(BigInteger.ZERO);
        savingsTargets.setSavedOere(0);
        savingsTargets.setAccountNumber(DBServiceAccount.randomNumber());
        savingsTargets.setName(name);
        savingsTargets.setTime(System.currentTimeMillis() / 1000);

        try {
            Dao<SavingsTargets, Integer> savingsTargetsIntegerDao = DaoManager.createDao(DBServiceConnection.connectionSource, SavingsTargets.class);
            savingsTargetsIntegerDao.create(savingsTargets);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }
// ---------------------------------- Some changes

    public static SavingsTargets addToTarget(SavingsTargets savingsTarget, BigInteger kroner, int oere) {
        if (validateInput(kroner, oere)) {
            if(savingsTarget.getSavedOere() + oere > 99){
                kroner.add(BigInteger.ONE);
                savingsTarget.setSavedOere(savingsTarget.getSavedOere() + oere - 100);
            }else{
                savingsTarget.setSavedOere(oere+ savingsTarget.getSavedOere());
            }
            savingsTarget.setSavedKroner(savingsTarget.getSavedKroner().add(kroner));
        }
        return savingsTarget;
    }

    public static SavingsTargets subtractFromTarget(SavingsTargets savingsTarget, BigInteger kroner, int oere) {
        savingsTarget.setSavedOere(savingsTarget.getSavedOere() - oere);
        savingsTarget.setSavedKroner(savingsTarget.getSavedKroner().subtract(kroner));
        return savingsTarget;
    }

    public static boolean updateTargetName(int savingTargetId, String name) {
        DBServiceConnection.makeConnection();
        SavingsTargets savingsTarget = getSavingsTarget(savingTargetId);
        savingsTarget.setName(name);
        return updateSavingsTarget(savingsTarget);
    }


    public static boolean updateTargetGoal(int savingsTargetId, BigInteger goalKroner, int goalOere) {
        DBServiceConnection.makeConnection();
        SavingsTargets savingsTargets = getSavingsTarget(savingsTargetId);

        if(validateInput(goalKroner, goalOere)){
            savingsTargets.setGoalKroner(goalKroner);
            savingsTargets.setGoalOere(goalOere);
        }

        return updateSavingsTarget(savingsTargets);
    }


    public static boolean updateSavingsTarget(SavingsTargets savingsTargets) {
        try {
            Dao<SavingsTargets, Integer> savingsTargetsIntegerDao = DaoManager.createDao(DBServiceConnection.connectionSource, SavingsTargets.class);
            savingsTargetsIntegerDao.update(savingsTargets);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }
}
