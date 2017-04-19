package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.Budget;
import no.westerdals.pj3100g15.ORM.BudgetCategory;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.sql.SQLException;
import java.util.List;

public class DBServiceBudget{

    /**
     *
     * Creates a budget in a database related to a specified customer.
     *
     * @param customerId is used to associate the customer to the budget in the database.
     * @return boolean
     */
    public static boolean createBudget(int customerId){
        DBServiceConnection.makeConnection();

        Budget budget = new Budget();
        budget.setCustomerId(customerId);
        try {
            Dao<Budget, Integer> budgetDao = DaoManager.createDao(DBServiceConnection.connectionSource, Budget.class);
            budgetDao.create(budget);
            return true;
        } catch (SQLException e){
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * Returns a Budget-object with the specified budgetId from the database.
     *
     * @param budgetId is used to find the budget-object in the database with the specified budgetId.
     * @return Budget (Object)
     */
    public static Budget getBudget(int budgetId){
        DBServiceConnection.makeConnection();

        try {
            Dao<Budget, Integer> budgetDao = DaoManager.createDao(DBServiceConnection.connectionSource, Budget.class);
            Budget budget = budgetDao.queryForId(budgetId);
            return budget;
        } catch (SQLException e){
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * Retrieves all the Budgets in the database.
     *
     * @return 'List<Budget>' a list with budgets.
     */
    public static List<Budget> getAllBudgets() {
        DBServiceConnection.makeConnection();
        try {
            Dao<Budget, Integer> budgetDao = DaoManager.createDao(DBServiceConnection.connectionSource, Budget.class);
            List<Budget> budgets = budgetDao.queryForAll();
            return budgets;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes a budget in the database with the given budgetId.
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
        } catch (SQLException e){
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    //-------------------- Methods for Budget Categories -------------------

    /**
     *
     *
     *
     * @param budgetId used to retrieve budgetobject
     * @param category String that defines a category for budgets
     * @param goal
     * @return boolean
     */
    public static boolean createBudgetCategory(int budgetId, String category, int goal) {
        DBServiceConnection.makeConnection();
        BudgetCategory budgetCategory = new BudgetCategory(budgetId, category, goal);
        if(getBudget(budgetId) == null){
            return false;
        }
        try{
            Dao<BudgetCategory, Integer> categoryDao = DaoManager.createDao(DBServiceConnection.connectionSource, BudgetCategory.class);
            categoryDao.create(budgetCategory);
            return true;
        } catch(SQLException e){
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param categoryId
     * @return
     */
    public static BudgetCategory getBudgetCategory(int categoryId){
        DBServiceConnection.makeConnection();
        try {
            Dao<BudgetCategory, Integer> categoryDao = DaoManager.createDao(DBServiceConnection.connectionSource, BudgetCategory.class);
            BudgetCategory budgetCategory = categoryDao.queryForId(categoryId);
            return budgetCategory;
        } catch (SQLException e){
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param budgetId
     * @return
     */
    public static List<BudgetCategory> getAllCategories(int budgetId){
        DBServiceConnection.makeConnection();

        try {
            Dao<BudgetCategory, Integer> categoryDao = DaoManager.createDao(DBServiceConnection.connectionSource, BudgetCategory.class);
            List<BudgetCategory> categories = categoryDao.queryForEq("BudsjettID", budgetId);

            return categories;
        } catch(SQLException e){
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    //------------------- Update methods for budget categories -------------------

    /**
     *
     * @param categoryId
     * @param name
     * @return
     */
    public static boolean updateCategoryName(int categoryId, String name){
        DBServiceConnection.makeConnection();
        BudgetCategory category = getBudgetCategory(categoryId);
        category.setCategory(name);
        return updateCategoryObject(category);
    }

    /**
     *
     * @param categoryId
     * @param goal
     * @return
     */
    public static boolean updateCategoryGoal(int categoryId, int goal){
        DBServiceConnection.makeConnection();
        BudgetCategory category = getBudgetCategory(categoryId);
        category.setGoalAmount(goal);
       return updateCategoryObject(category);
    }

    /**
     *
     * @param categoryId
     * @param used
     * @return
     */
    public static boolean updateCategoryUsed(int categoryId, int used){
        DBServiceConnection.makeConnection();
        BudgetCategory category = getBudgetCategory(categoryId);

        int amount = category.getUsedAmount() + used;
        category.setUsedAmount(amount);

        return updateCategoryObject(category);
    }

    /**
     *
     * @param category
     * @return
     */
    public static boolean updateCategoryObject(BudgetCategory category){
        try{
            Dao<BudgetCategory, Integer> categoryDao = DaoManager.createDao(DBServiceConnection.connectionSource, BudgetCategory.class);
            categoryDao.update(category);
            return true;
        } catch(SQLException e){
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param categoryId
     * @return
     */
    public static boolean deleteBudgetCategory(int categoryId){
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