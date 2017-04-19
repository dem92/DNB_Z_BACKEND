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

@Controller
public class RequestMapAccount {

    @RequestMapping(value = "/user/account/{accountID}", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccount(@PathVariable(value = "accountID") String account) {
        Account account1 = DBServiceAccount.getAccount(account);
        //DBServiceConnection.closeConnection();
        return account1;
    }

    @RequestMapping(value = "/user/{id}/account/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAccounts(@PathVariable(value = "id") int customerId) {
        List<Account> accounts = DBServiceAccount.getCustomerAccounts(customerId);
        //DBServiceConnection.closeConnection();
        return accounts;
    }

    @RequestMapping(value = "/user/all/account/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAllAccounts() {
        List<Account> accounts = DBServiceAccount.getAllAccounts();
        //DBServiceConnection.closeConnection();
        return accounts;
    }

    @RequestMapping(value = "/user/{id}/newaccount/{accounttype}", method = RequestMethod.GET)
    @ResponseBody
    public boolean createAccount(
            @PathVariable(value = "id") int customerId,
            @PathVariable(value = "accounttype") String accountType) {
        if (DBServiceAccount.addAccount(customerId, accountType)) {
            //DBServiceConnection.closeConnection();
            return true;
        }
        DBServiceConnection.closeConnection();
        return false;
    }

    // --------------------    oppdatering av konto under denne streken    -------------------
    @RequestMapping(value = "/updateaccount/{accountid}/main/{main}")
    @ResponseBody
    public boolean updateAccountMain(@PathVariable(value = "accountid") String accountId,
                                     @PathVariable(value = "main") int main) {

        if (DBServiceAccount.updateMain(accountId, main)) {
            //DBServiceConnection.closeConnection();
            return true;
        } else {
            //DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateaccount/{accountid}/accountname/{accountname}")
    @ResponseBody
    public boolean updateAccountName(@PathVariable(value = "accountid") String accountId,
                                     @PathVariable(value = "accountname") String accountname) {

        if (DBServiceAccount.updateAccountname(accountId, accountname)) {
            //DBServiceConnection.closeConnection();
            return true;
        } else {
            //DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/deleteaccount/{accountid}")
    @ResponseBody
    public boolean deleteAccount(@PathVariable(value = "accountid") String accountId) {
        if (DBServiceAccount.deleteAccount(accountId)) {
            //DBServiceConnection.closeConnection();
            return true;
        }
        //DBServiceConnection.closeConnection();
        return false;
    }
}
