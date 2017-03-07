package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.ORM.Account;
import no.westerdals.pj3100g15.ORM.Customer;
import no.westerdals.pj3100g15.ORM.DBService;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.sql.SQLException;
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
    public List<Account> getAccounts(@PathVariable(value = "id") int customerId) {
        return DBService.getCustomerAccounts(customerId);
    }

    @RequestMapping(value = "/user/all/account/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAllAccounts() {
        return DBService.getAllAccounts();
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomer(@PathVariable(value = "id") int customerId) {
        return DBService.getCustomer(customerId);
    }

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getAllCustomers() {
        return DBService.getAllCustomers();
    }

    @RequestMapping(value = "/user/{id}/auth", method = RequestMethod.GET)
    @ResponseBody
    public String[] getPasswords(@PathVariable(value = "id") String birthdayNumber) {
        return DBService.getPassword(birthdayNumber);
    }

    @RequestMapping(value = "/newuser/{firstname}/{surname}/{email}/{birthdaynumber}", method = RequestMethod.GET)
    @ResponseBody
    public boolean createNewUser(
            @PathVariable(value = "firstname") String firstName,
            @PathVariable(value = "surname") String surName,
            @PathVariable(value = "birthdaynumber") String birthdaynumber,
            @PathVariable(value = "email") String email) {
        if (DBService.addCustomer(firstName, surName, birthdaynumber, email)) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "/user/{id}/newaccount/{accounttype}", method = RequestMethod.GET)
    @ResponseBody
    public boolean createAccount(
            @PathVariable(value = "id") int customerId,
            @PathVariable(value = "accounttype") String accountType) {
        if (DBService.addAccount(customerId, accountType)) {
            return true;
        }
        return false;
    }

    //TODO Se metoden sendMoney i DBService för info om vad detta är.
    @RequestMapping(value = "/{sendersAccount}/{recieversAccount}/{kroner}/{oere}", method = RequestMethod.GET)
    @ResponseBody
    public boolean sendMoney(
            @PathVariable(value = "sendersAccount") String accountNumber,
            @PathVariable(value = "recieversAccount") String accountNumber2,
            @PathVariable(value = "kroner") BigInteger kroner,
            @PathVariable(value = "oere") int oere) {
        if (DBService.sendMoney(accountNumber, accountNumber2, kroner, oere)) {
            return true;
        } else {
            return false;
        }
    }


    // -------------------    Oppdatere customer under denne streken   ---------------------

    @RequestMapping(value = "/updateuser/{customerid}/surname/{surname}")
    @ResponseBody
    public boolean updateCustomerSurname(@PathVariable(value = "customerid") int customerId,
                                         @PathVariable(value = "surname") String surname) {

        if (DBService.updateSurname(customerId, surname)) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/firstname/{firstname}")
    @ResponseBody
    public boolean updateCustomerFirstName(@PathVariable(value = "customerid") int customerId,
                                           @PathVariable(value = "firstname") String firstName) {

        if (DBService.updateFirstname(customerId, firstName)) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/address/{address}")
    @ResponseBody
    public boolean updateCustomerAddress(@PathVariable(value = "customerid") int customerId,
                                         @PathVariable(value = "address") String address) {

        if (DBService.updateAddress(customerId, address)) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/postalcode/{postalcode}")
    @ResponseBody
    public boolean updateCustomerPostalCode(@PathVariable(value = "customerid") int customerId,
                                            @PathVariable(value = "postalcode") int postalCode) {

        if (DBService.updatePostalcode(customerId, postalCode)) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/email/{email}")
    @ResponseBody
    public boolean updateCustomerEmail(@PathVariable(value = "customerid") int customerId,
                                       @PathVariable(value = "email") String email) {

        if (DBService.updateEmail(customerId, email)) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/phonenumber/{phone}")
    @ResponseBody
    public boolean updateCustomerPhone(@PathVariable(value = "customerid") int customerId,
                                       @PathVariable(value = "phone") int phone) {

        if (DBService.updatePhone(customerId, phone)) {
            return true;
        } else {
            return false;
        }
    }


    // --------------------    oppdatering av konto under denne streken    -------------------
    @RequestMapping(value = "/updateaccount/{accountid}/main/{main}")
    @ResponseBody
    public boolean updateAccountMain(@PathVariable(value = "accountid") String accountId,
                                     @PathVariable(value = "main") int main) {

        if (DBService.updateMain(accountId, main)) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(value = "/updateaccount/{accountid}/accountname/{accountname}")
    @ResponseBody
    public boolean updateAccountMain(@PathVariable(value = "accountid") String accountId,
                                     @PathVariable(value = "accountname") String accountname) {

        if (DBService.updateAccountname(accountId, accountname)) {
            return true;
        } else {
            return false;
        }
    }

    // ------------ sletting av bruker eller konto  ----------------

    @RequestMapping(value = "deleteuser/{customerid}")
    @ResponseBody
    public boolean deleteCustomer(@PathVariable(value = "customerid") int customerId) {
        if (DBService.deleteUser(customerId)) {
            return true;
        }
        return false;
    }

    @RequestMapping(value = "deleteaccount/{accountid}")
    @ResponseBody
    public boolean deleteAccount(@PathVariable(value = "accountid") String accountId) {
        if (DBService.deleteAccount(accountId)) {
            return true;
        }
        return false;
    }


}
