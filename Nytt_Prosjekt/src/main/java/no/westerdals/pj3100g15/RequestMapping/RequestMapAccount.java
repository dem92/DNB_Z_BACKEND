/*
 * Copyright (c) 2017. Group 15, PJ-3100, Westerdals Oslo ACT
 */

package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.DBService.DBServiceAccount;
import no.westerdals.pj3100g15.DBService.DBServiceConnection;
import no.westerdals.pj3100g15.ORM.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static no.westerdals.pj3100g15.DBService.DBServiceAccount.*;

@Controller
public class RequestMapAccount {

    /**
     *  This request map runs a method to get an existing Account-object
     * @param account is an accountnumber pointing to a specific account
     * @return an Account-object
     */
    @RequestMapping(value = "/user/account/{accountID}", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccount(@PathVariable(value = "accountID") String account) {
        return getAccount(account);
    }

    /**
     * This request map runs a method to get all accounts for a specific user
     * @param customerId is the ID for a user
     * @return a List<> of accounts for a specific user
     */
    @RequestMapping(value = "/user/{id}/account/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAccounts(@PathVariable(value = "id") int customerId) {
        return getCustomerAccounts(customerId);
    }

    /**
     * This request map runs a method to get all accounts from the database
     * @return a List<> of every account in the database
     */
    @RequestMapping(value = "/user/all/account/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAllAccounts() {
        return getAllAccounts();
    }

    /**
     * This request map runs a method to create a new account for a user
     * @param customerId is the ID for a user
     * @param accountType is a String specifying the account type (so far only brukskonto or sparekonto)
     * @return a boolean to see if an account was successfully created
     */
    @RequestMapping(value = "/user/{id}/newaccount/{accounttype}", method = RequestMethod.GET)
    @ResponseBody
    public boolean createAccount(
            @PathVariable(value = "id") int customerId,
            @PathVariable(value = "accounttype") String accountType) {
        return addAccount(customerId, accountType);

    }

    /**
     * This request map runs a method to update whether an account is a main account or not
     * @param accountId is the account number for the specified account
     * @param main is set to either 0 (not main) or 1 (main)
     * @return a boolean to see if the change was successful
     */
    @RequestMapping(value = "/updateaccount/{accountid}/main/{main}")
    @ResponseBody
    public boolean updateAccountMain(@PathVariable(value = "accountid") String accountId,
                                     @PathVariable(value = "main") int main) {
       return updateMain(accountId, main);
    }

    /**
     * This request map runs a method to update the account name
     * @param accountId is the account number for the specified account
     * @param accountname is a string for the new account name
     * @return a boolean to see if the change was successful
     */
    @RequestMapping(value = "/updateaccount/{accountid}/accountname/{accountname}")
    @ResponseBody
    public boolean updateAccountName(@PathVariable(value = "accountid") String accountId,
                                     @PathVariable(value = "accountname") String accountname) {
        return updateAccountname(accountId, accountname);
    }

    /**
     * This request map runs a method to delete an account
     * @param accountId is the account number for the specified account
     * @return a boolean to see if it was successfully deleted
     */
    @RequestMapping(value = "/deleteaccount/{accountid}")
    @ResponseBody
    public boolean deleteAccount(@PathVariable(value = "accountid") String accountId) {
        return deleteAccount(accountId);
    }
}
