/*
 * Copyright (c) 2017. Group 15, PJ-3100, Westerdals Oslo ACT
 */

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
    /**
     * This request map runs a method to create a new budget for a user
     *
     * @param userId is a unique ID number for a user
     * @return a boolean to see if it was successfully created
     */
    @RequestMapping(value = "/newbudget/{userid}", method = RequestMethod.GET)
    @ResponseBody
    public boolean createBudget(@PathVariable(value = "userid") int userId) {
        return DBServiceBudget.createBudget(userId);
    }

    /**
     * This request map runs a method to get an existing budget
     *
     * @param budgetId is a unique ID number for a budget
     * @return a Budget-object with values from the database
     */
    @RequestMapping(value = "/budget/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Budget getBudget(@PathVariable(value = "id") int budgetId) {
        return DBServiceBudget.getBudget(budgetId);
    }

    /**
     * This request map runs a method to get all budgets from the database
     *
     * @return a List<> with all existing budgets
     */
    @RequestMapping(value = "/budget/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Budget> getAllBudgets() {
        return DBServiceBudget.getAllBudgets();
    }

    /**
     * This request map runs a method for deleting a budget
     *
     * @param budgetId is a unique ID number for a budget
     * @return a boolean to see if it was successfully deleted
     */
    @RequestMapping(value = "/deletebudget/{id}", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteBudget(@PathVariable(value = "id") int budgetId) {
        return DBServiceBudget.deleteBudget(budgetId);
    }

    /**
     * This request map runs a method that creates a new budget category for a specified budget
     *
     * @param budgetId is a unique ID number for a budget
     * @param category is the name for the new category
     * @param goal     is the maximum amount the user want to spend on this category
     * @return a boolean to see if the budget category was successfully created
     */
    @RequestMapping(value = "/newbudgetcategory/{budgetid}/{name}/{goal}")
    @ResponseBody
    public boolean createBudgetCategory(
            @PathVariable(value = "budgetid") int budgetId,
            @PathVariable(value = "name") String category,
            @PathVariable(value = "goal") int goal) {
        return DBServiceBudget.createBudgetCategory(budgetId, category, goal);
    }

    /**
     * This request map runs a method that gets a specific category
     *
     * @param id is a unique ID number for a budget category
     * @return a BudgetCategory-object
     */
    @RequestMapping(value = "/budgetcategory/{id}")
    @ResponseBody
    public BudgetCategory getBudgetCategory(@PathVariable(value = "id") int id) {
        return DBServiceBudget.getBudgetCategory(id);
    }

    /**
     * This request map runs a method that gets all budget categories in a specified budget
     *
     * @param id is a unique ID number for a budget
     * @return a List<> with all BudgetCategories in the specified budget
     */
    @RequestMapping(value = "/budgetcategory/all/{id}")
    @ResponseBody
    public List<BudgetCategory> getAllBudgetCategories(@PathVariable(value = "id") int id) {
        return DBServiceBudget.getAllCategories(id);
    }

    /**
     * This request map runs a method that updates the name of a budget category
     *
     * @param id   is a unique ID number for a budget category
     * @param name is a string for the new name
     * @return a boolean to see if the change was successful
     */
    @RequestMapping(value = "/updateCategory/{id}/name/{name}")
    @ResponseBody
    public boolean updateCategoryName(@PathVariable(value = "id") int id,
                                      @PathVariable(value = "name") String name) {
        return DBServiceBudget.updateCategoryName(id, name);
    }

    /**
     * This request map runs a method that updates the budget category goal
     *
     * @param id   is a unique ID number for a budget category
     * @param goal is the new amount that the user want to spend on this category
     * @return a boolean to see if the change was successful
     */
    @RequestMapping(value = "/updateCategory/{id}/goal/{goal}")
    @ResponseBody
    public boolean updateCategoryGoal(@PathVariable(value = "id") int id,
                                      @PathVariable(value = "goal") int goal) {
        return DBServiceBudget.updateCategoryGoal(id, goal);
    }

    /**
     * This request map runs a method that updates how much a user has spent on this category
     *
     * @param id   is a unique ID number for a budget category
     * @param used is the amount the user adds to the category
     * @return a boolean to see if the change was successful
     */
    @RequestMapping(value = "/updateCategory/{id}/used/{used}")
    @ResponseBody
    public boolean updateCategoryUsed(@PathVariable(value = "id") int id,
                                      @PathVariable(value = "used") int used) {
        return DBServiceBudget.updateCategoryUsed(id, used);
    }

    /**
     * This request map runs a method that deletes a budget category
     *
     * @param categoryId is a unique ID number for a budget category
     * @return a boolean to see if the category was successfully deleted
     */
    @RequestMapping(value = "/deletebudgetitem/{id}", method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteBudgetCategory(@PathVariable(value = "id") int categoryId) {
        return DBServiceBudget.deleteBudgetCategory(categoryId);
    }
}