package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.Budget;
import no.westerdals.pj3100g15.ORM.BudgetCategory;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.sql.SQLException;
import java.util.List;

public class DBServiceBudget{

    //--------------- Methods for creating, getting and deleting Budget ---------------
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
    public static boolean updateCategoryName(int categoryId, String name){
        DBServiceConnection.makeConnection();
        BudgetCategory category = getBudgetCategory(categoryId);
        category.setCategory(name);
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

    public static boolean updateCategoryGoal(int categoryId, int goal){
        DBServiceConnection.makeConnection();
        BudgetCategory category = getBudgetCategory(categoryId);
        category.setGoalAmount(goal);
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

    public static boolean updateCategoryUsed(int categoryId, int used){
        DBServiceConnection.makeConnection();
        BudgetCategory category = getBudgetCategory(categoryId);

        int amount = category.getUsedAmount() + used;
        category.setUsedAmount(amount);

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