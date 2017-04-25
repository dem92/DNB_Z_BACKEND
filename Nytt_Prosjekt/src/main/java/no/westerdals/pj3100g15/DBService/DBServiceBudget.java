/*
 * Copyright (c) 2017. Group 15, PJ-3100, Westerdals Oslo ACT
 */

package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.Budget;
import no.westerdals.pj3100g15.ORM.BudgetCategory;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.sql.SQLException;
import java.util.List;

public class DBServiceBudget {

    /**
     * Creates a budget in a database related to a specified customer.
     *
     * @param customerId is used to associate the customer to the budget in the database.
     * @return boolean
     */
    public static boolean createBudget(int customerId) {
        DBServiceConnection.makeConnection();

        Budget budget = new Budget();
        budget.setCustomerId(customerId);
        try {
            Dao<Budget, Integer> budgetDao = DaoManager.createDao(DBServiceConnection.connectionSource, Budget.class);
            budgetDao.create(budget);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Returns a Budget-object with the specified budgetId from the database.
     *
     * @param budgetId is used to find the budget-object in the database with the specified budgetId.
     * @return Budget (Object)
     */
    public static Budget getBudget(int budgetId) {
        DBServiceConnection.makeConnection();

        try {
            Dao<Budget, Integer> budgetDao = DaoManager.createDao(DBServiceConnection.connectionSource, Budget.class);
            return budgetDao.queryForId(budgetId);
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all the Budgets in the database.
     *
     * @return 'List<Budget>' a list with budgets.
     */
    public static List<Budget> getAllBudgets() {
        DBServiceConnection.makeConnection();
        try {
            Dao<Budget, Integer> budgetDao = DaoManager.createDao(DBServiceConnection.connectionSource, Budget.class);
            return budgetDao.queryForAll();
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes a budget in the database with the given budgetId.
     *
     * @param budgetId an int that is used to retrieve the Budget-object from the database.
     * @return boolean
     */
    public static boolean deleteBudget(int budgetId) {
        DBServiceConnection.makeConnection();
        Budget budget = getBudget(budgetId);
        try {
            Dao<Budget, Integer> budgetDao = DaoManager.createDao(DBServiceConnection.connectionSource, Budget.class);
            budgetDao.delete(budget);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    //-------------------- Methods for Budget Categories -------------------

    /**
     * @param budgetId used to retrieve budgetobject
     * @param category String that defines a category for budgets
     * @param goal     sets goal of the budgetcategory.
     * @return boolean
     */
    public static boolean createBudgetCategory(int budgetId, String category, int goal) {
        DBServiceConnection.makeConnection();
        BudgetCategory budgetCategory = new BudgetCategory(budgetId, category, goal);
        if (getBudget(budgetId) == null) {
            return false;
        }
        try {
            Dao<BudgetCategory, Integer> categoryDao = DaoManager.createDao(DBServiceConnection.connectionSource, BudgetCategory.class);
            categoryDao.create(budgetCategory);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @param categoryId specifies which budgetcategory to be returned from the database.
     * @return BudgetCategory
     */
    public static BudgetCategory getBudgetCategory(int categoryId) {
        DBServiceConnection.makeConnection();
        try {
            Dao<BudgetCategory, Integer> categoryDao = DaoManager.createDao(DBServiceConnection.connectionSource, BudgetCategory.class);
            return categoryDao.queryForId(categoryId);
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Used to retrieve all BudgetCategories associated with a Budget in the database.
     *
     * @param budgetId used to retrieve the specified budget.
     * @return List with BudgetCategory-objects
     */
    public static List<BudgetCategory> getAllCategories(int budgetId) {
        DBServiceConnection.makeConnection();

        try {
            Dao<BudgetCategory, Integer> categoryDao = DaoManager.createDao(DBServiceConnection.connectionSource, BudgetCategory.class);
            return categoryDao.queryForEq("BudsjettID", budgetId);
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    //------------------- Update methods for budget categories -------------------

    /**
     * is used to update the categorys name in the database.
     *
     * @param categoryId specifies the category that is to be changed.
     * @param name       the new name of the category
     * @return boolean
     */
    public static boolean updateCategoryName(int categoryId, String name) {
        DBServiceConnection.makeConnection();
        BudgetCategory category = getBudgetCategory(categoryId);
        category.setCategory(name);
        return updateCategoryObject(category);
    }

    /**
     * Used to update the categorygoal
     *
     * @param categoryId specifies the id of the category to be changed.
     * @param goal       the new goal.
     * @return boolean
     */
    public static boolean updateCategoryGoal(int categoryId, int goal) {
        DBServiceConnection.makeConnection();
        BudgetCategory category = getBudgetCategory(categoryId);
        category.setGoalAmount(goal);
        return updateCategoryObject(category);
    }

    /**
     * update the how many times the category is used. The amount of times is just added to the current amount.
     *
     * @param categoryId specifies which category in the database.
     * @param used       the amount of additional times it is used.
     * @return boolean
     */
    public static boolean updateCategoryUsed(int categoryId, int used) {
        DBServiceConnection.makeConnection();
        BudgetCategory category = getBudgetCategory(categoryId);
        category.setUsedAmount(category.getUsedAmount() + used);
        return updateCategoryObject(category);
    }

    /**
     * Utilmethod to update the whole Budgetcategoryobject in the database.
     *
     * @param category is the object to be updated.
     * @return boolean
     */
    private static boolean updateCategoryObject(BudgetCategory category) {
        try {
            Dao<BudgetCategory, Integer> categoryDao = DaoManager.createDao(DBServiceConnection.connectionSource, BudgetCategory.class);
            categoryDao.update(category);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes the budgetcategory with the given ID in the database.
     *
     * @param categoryId specifies which category to retrieve from the database.
     * @return boolean
     */
    public static boolean deleteBudgetCategory(int categoryId) {
        DBServiceConnection.makeConnection();
        BudgetCategory category = getBudgetCategory(categoryId);
        try {
            Dao<BudgetCategory, Integer> categoryDao = DaoManager.createDao(DBServiceConnection.connectionSource, BudgetCategory.class);
            categoryDao.delete(category);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }
}