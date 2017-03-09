package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.SavingsTargets;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

public class DBServiceSavingsTargets {
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

    public static boolean createSavingsTarget(BigInteger goalKroner, int goalOere, int customerId, String name){
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
        savingsTargets.setTime(System.currentTimeMillis()/1000);

        try{
            Dao<SavingsTargets, Integer> savingsTargetsIntegerDao= DaoManager.createDao(DBServiceConnection.connectionSource, SavingsTargets.class);
            savingsTargetsIntegerDao.create(savingsTargets);
            return true;
        }catch (SQLException e){
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }
}
