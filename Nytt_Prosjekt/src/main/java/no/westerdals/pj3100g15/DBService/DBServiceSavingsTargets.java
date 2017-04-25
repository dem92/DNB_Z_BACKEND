/*
 * Copyright (c) 2017. Group 15, PJ-3100, Westerdals Oslo ACT
 */

package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.SavingsTargets;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import static no.westerdals.pj3100g15.ValidateData.ValidateInput.validateInput;

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

    public static SavingsTargets addToTarget(SavingsTargets savingsTarget, BigInteger kroner, int oere) {
        if (validateInput(kroner, oere)) {
            if (savingsTarget.getSavedOere() + oere > 99) {
                kroner.add(BigInteger.ONE);
                savingsTarget.setSavedOere(savingsTarget.getSavedOere() + oere - 100);
            } else {
                savingsTarget.setSavedOere(oere + savingsTarget.getSavedOere());
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

        if (validateInput(goalKroner, goalOere)) {
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

    public static boolean lessOereOnSavingsTargetThanSentOere(SavingsTargets savingsTargets, int oere) {
        if (savingsTargets.getSavedOere() < oere) {
            return true;
        }
        return false;
    }

    public static SavingsTargets subtractOneKroneAndAdd100OereToSavingsTargets(SavingsTargets savingsTargets) {
        savingsTargets.setSavedKroner(savingsTargets.getSavedKroner().subtract(BigInteger.ONE));
        savingsTargets.setSavedOere(savingsTargets.getSavedOere() + 100);
        return savingsTargets;
    }

    /**
     * Checks if the savingsTarget has more kroner and oere than its goal
     * or checks if the kroner is larger and the oere is less than the goal.
     *
     * @param savingsTarget is the savingstarget object.
     * @return boolean
     */
    public static boolean checkIfTargetIsDone(SavingsTargets savingsTarget) {

        //Checks if the savingstarget has more than or the same kroner/oere compared to the goal
        if (savingsTarget.getSavedKroner().compareTo(savingsTarget.getGoalKroner()) >= 0 && savingsTarget.getSavedOere() >= savingsTarget.getGoalOere()) {
            return true;
        }

        //Checks if the savingstarget has more kroner and less oere than its goals
        else if (savingsTarget.getSavedKroner().compareTo(savingsTarget.getGoalKroner()) > 0 && savingsTarget.getSavedOere() < savingsTarget.getGoalOere()) {
            subtractOneKroneAndAdd100OereToSavingsTargets(savingsTarget);
            return true;
        }

        return false;
    }

    /**
     * This method sets the "isDone"-parameter in a savingstargets to done.
     * Returns true if the update in the database is complete.
     *
     * @param savingsTargets is a savingstargets-object.
     * @return boolean
     */
    public static boolean targetIsDone(SavingsTargets savingsTargets) {
        savingsTargets.setDone(true);
        return updateSavingsTarget(savingsTargets);
    }

    /**
     * Gets the overflowing kroner when comparing the goal to the saved kroner on the savingstarget.
     *
     * @param savingsTargets is used to check the goal to the actually saved kroner
     * @return Biginteger
     */
    public static BigInteger getOverflowingKronerOfDoneSavingsTarget(SavingsTargets savingsTargets) {
        if (savingsTargets.getGoalKroner().compareTo(savingsTargets.getSavedKroner()) < 0) {
            return savingsTargets.getSavedKroner().subtract(savingsTargets.getGoalKroner());
        }
        return BigInteger.ZERO;
    }

    /**
     * Gets the overflowing oere when comparing the goal to the saved oere one the savingstarget.
     *
     * @param savingsTargets provides the savingstargets-object to check.
     * @return Integer
     */
    public static int getOverflowingOereOfDoneSavingsTarget(SavingsTargets savingsTargets) {
        if (savingsTargets.getGoalOere() < savingsTargets.getSavedOere()) {
            return savingsTargets.getSavedOere() - savingsTargets.getGoalOere();
        }
        return 0;

    }
}
