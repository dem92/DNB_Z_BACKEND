package no.westerdals.pj3100g15.RequestMapping;

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
/* TODO endre URL
    @RequestMapping("/account")
    @ResponseBody
    public String getSaldoForAccount(@RequestParam(value = "kontonummer", defaultValue = "80766645136") String kontonummer) {
        DBServiceAccount dbServiceAccount=new DBServiceAccount();
        String text ="";
        try{
            text = dbServiceAccount.saldo(kontonummer);}catch (SQLException e){e.printStackTrace();}
        return text;
    }*/
}
