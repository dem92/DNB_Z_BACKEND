package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.SavingsTargets;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

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

    public static boolean addToTarget(String accountNumber, int savingsTargetId, BigInteger kroner, int oere) {
        DBServiceConnection.makeConnection();
        SavingsTargets savingsTarget = getSavingsTarget(savingsTargetId);
        if (validateTargetOere(oere)) {
            return false;
        }
        if(savingsTarget.getSavedOere() + oere > 99){
            kroner.add(BigInteger.ONE);
            savingsTarget.setSavedOere(savingsTarget.getSavedOere() + oere - 100);
        }else{
            savingsTarget.setSavedOere(oere+ savingsTarget.getSavedOere());
        }
        savingsTarget.setSavedKroner(savingsTarget.getSavedKroner().add(kroner));

        return false;
    }

    public static boolean subtractFromTarget(String accountNumber, int savingsTargetId, BigInteger kroner, int oere) {
        

        return false;
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

        if (goalOere > 99 || goalOere < 0) {
            return false;
        } else {
            savingsTargets.setGoalOere(goalOere);
        }

        if(goalKroner.compareTo(BigInteger.ZERO) >= 0){
            savingsTargets.setGoalKroner(goalKroner);
        }
        return updateSavingsTarget(savingsTargets);
    }

    public static boolean validateTargetOere(int oere){
        if (oere > 99 || oere < 0) {
            return false;
        }
        return true;
    }

    // --------------------------------  SomeChanges Stop


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
