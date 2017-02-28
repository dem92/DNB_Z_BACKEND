package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.ORM.Account;
import no.westerdals.pj3100g15.ORM.Customer;
import no.westerdals.pj3100g15.ORM.DBService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Eva Dahlo on 24/11/2016.
 */
@RestController
public class SpringRequestMapController {

    @RequestMapping(value = "/user/account/{accountID}", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccount(@PathVariable(value = "accountID") String account) {
        return DBService.getAccount(account);
    }

    @RequestMapping(value = "/user/{id}/account/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAccounts(@PathVariable(value = "id") String userID) {
        return DBService.getCustomerAccounts(userID);
    }

    @RequestMapping(value = "/user/all/account/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAllAccounts() {
        return DBService.getAllAccounts();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomer(@PathVariable(value = "id") String userID) {
        return DBService.getCustomer(userID);
    }

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getAllCustomers() {
        return DBService.getAllCustomers();
    }

    @RequestMapping(value = "/user/{id}/auth", method = RequestMethod.GET)
    @ResponseBody
    public String[] getPasswords(@PathVariable(value = "id") String userID) {
        return DBService.getPassword(userID);
    }

    @RequestMapping(value = "/user/new/{phonenumber/{postalcode}/{address}/{birthdaynumber}/{email}/{firstname}/{surname}", method = RequestMethod.GET)
    @ResponseBody
    public void createNewUser(@PathVariable(value = "phonenumber") String phoneNumber,
                              @PathVariable(value = "postalcode")String postCode,
                              @PathVariable(value = "address") String address,
                              @PathVariable(value = "birthdaynumber") String birthdaynumber,
                              @PathVariable(value = "email") String email,
                              @PathVariable(value = "firstname") String firstName,
                              @PathVariable(value = "surname") String surName){
        DBService.addCustomer(phoneNumber,Integer.parseInt(postCode),address,birthdaynumber,email,firstName,surName);
    }

    @RequestMapping(value = "/user/{id}/account/new/{accounttype}", method = RequestMethod.GET)
    @ResponseBody
    public void createAccount(@PathVariable(value = "id")String customerId,
                              @PathVariable(value = "accounttype") String accountType){
        int customerId2 = Integer.parseInt(customerId);
        DBService.addAccount(customerId2,accountType);
    }


}
