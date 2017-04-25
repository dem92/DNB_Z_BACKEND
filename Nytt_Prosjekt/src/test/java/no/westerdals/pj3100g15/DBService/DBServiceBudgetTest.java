/*
 * Copyright (c) 2017. Group 15, PJ-3100, Westerdals Oslo ACT
 */

package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.Budget;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class DBServiceBudgetTest {

    Optional<Budget> budget;

    @Before
    public void doBeforeTest(){
        DBServiceBudget.createBudget(100);
        List<Budget> budgets = null;
        try {
            Dao<Budget, Integer> budgetDao = DaoManager.createDao(DBServiceConnection.connectionSource, Budget.class);
            budgets = budgetDao.queryForAll();
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        budget = budgets.stream().filter(someBudget -> someBudget.getCustomerId() == 100).findFirst();
    }

    @Test
    public void createAndDeleteBudget(){
        assertTrue(budget.isPresent());
        assertTrue(DBServiceBudget.deleteBudget(budget.get().getCustomerId()));
    }
}
