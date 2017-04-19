package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.DBService.DBServiceBudget;
import no.westerdals.pj3100g15.DBService.DBServiceConnection;
import no.westerdals.pj3100g15.ORM.Budget;
import no.westerdals.pj3100g15.ORM.BudgetCategory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RequestMapBudget {
    @RequestMapping(value = "/newbudget/{userid}", method = RequestMethod.GET)
    @ResponseBody
    public boolean createBudget(@PathVariable(value = "userid") int userId) {
        if(DBServiceBudget.createBudget(userId)) {
            return true;
        }
        DBServiceConnection.closeConnection();
        return false;
    }

    @RequestMapping(value = "/budget/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Budget getBudget(@PathVariable(value = "id") int budgetId) {
        Budget budget = DBServiceBudget.getBudget(budgetId);
        if (budget == null) return null;

        return budget;
    }

    @RequestMapping(value = "/deletebudget/{id}", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteBudget(@PathVariable(value = "id") int budgetId) {
        if (DBServiceBudget.deleteBudget(budgetId)) {
            return true;
        }
        return false;
    }

    //------------------ RequestMapping for budget categories ---------------------
    @RequestMapping(value = "/newbudgetcategory/{budgetid}/{name}/{goal}")
    @ResponseBody
    public boolean createBudgetCategory(
            @PathVariable(value = "budgetid") int budgetId,
            @PathVariable(value = "name") String category,
            @PathVariable(value = "goal") int goal) {
        if (DBServiceBudget.createBudgetCategory(budgetId, category, goal)) return true;

        else return false;
    }

    @RequestMapping(value = "/budgetcategory/{id}")
    @ResponseBody
    public BudgetCategory getBudgetCategory(@PathVariable(value = "id") int id) {
        BudgetCategory category = DBServiceBudget.getBudgetCategory(id);
        if(category == null) return null;

        return category;
    }

    @RequestMapping(value = "/budgetcategory/all/{id}")
    @ResponseBody
    public List<BudgetCategory> getAllBudgetCategories(@PathVariable(value = "id") int id) {
        List<BudgetCategory> categories = DBServiceBudget.getAllCategories(id);
        if(categories == null) return null;

        return categories;
    }

    @RequestMapping(value = "/updateCategory/{id}/name/{name}")
    @ResponseBody
    public boolean updateCategoryName (@PathVariable(value = "id") int id,
                                       @PathVariable(value = "name") String name) {
        if (DBServiceBudget.updateCategoryName(id, name)) return true;

        else return false;
    }

    @RequestMapping(value = "/updateCategory/{id}/goal/{goal}")
    @ResponseBody
    public boolean updateCategoryGoal (@PathVariable(value = "id") int id,
                                       @PathVariable(value = "goal") int goal) {
        if (DBServiceBudget.updateCategoryGoal(id, goal)) return true;

        else return false;
    }

    @RequestMapping(value = "/updateCategory/{id}/used/{used}")
    @ResponseBody
    public boolean updateCategoryUsed (@PathVariable(value = "id") int id,
                                       @PathVariable(value = "used") int used) {
        if (DBServiceBudget.updateCategoryUsed(id, used)) return true;

        else return false;
    }

    @RequestMapping(value = "/deletebudgetitem/{id}", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteBudgetCategory(@PathVariable(value = "id") int categoryId) {
        if (DBServiceBudget.deleteBudgetCategory(categoryId)) {
            return true;
        }
        return false;
    }
}