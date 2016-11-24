package no.westerdals.pj3100g15.users;

import no.westerdals.pj3100g15.ORM.Account;
import no.westerdals.pj3100g15.ORM.DBService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Eva Dahlo on 24/11/2016.
 */
@RestController
public class SpringAccountRetrievalController {

    @RequestMapping(value = "/user/{userID}/account/{accountID}", method = RequestMethod.GET)
    @ResponseBody
    public Account getAccount(@PathVariable(value = "accountID") String string){
        return DBService.getAccount(string);
    }

    @RequestMapping(value = "/user/{id}/accounts", method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAccounts(@PathVariable(value = "id") String string){
        return DBService.getCustomerAccounts(string);
    }
}
