package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.ORM.Account;
import no.westerdals.pj3100g15.ORM.Customer;
import no.westerdals.pj3100g15.ORM.DBService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @RequestMapping(value = "/user/new/{firstname}/{surname}/{email}/{birthdaynumber}/{customerid}/{addresse}/{postnummer}/{telefonnummer}", method = RequestMethod.GET)
    @ResponseBody
    public void createNewUser(
            @PathVariable(value = "firstname") String firstName,
            @PathVariable(value = "surname") String surName,
            @PathVariable(value = "birthdaynumber") String birthdaynumber,
            @PathVariable(value = "email") String email,
            @PathVariable(value = "customerid") int customerId,
            @PathVariable(value = "addresse")String address,
            @PathVariable(value = "postnummer") int postalCode,
            @PathVariable(value = "telefonnummer") int telephoneNumber) {
        DBService.addCustomer(firstName, surName, birthdaynumber, email, customerId, address, postalCode, telephoneNumber);
    }

    @RequestMapping(value = "/user/{id}/account/new/{accounttype}", method = RequestMethod.GET)
    @ResponseBody
    public void createAccount(@PathVariable(value = "id") int customerId,
                              @PathVariable(value = "accounttype") int accountType) {
        DBService.addAccount(customerId, accountType);
    }

    


}
