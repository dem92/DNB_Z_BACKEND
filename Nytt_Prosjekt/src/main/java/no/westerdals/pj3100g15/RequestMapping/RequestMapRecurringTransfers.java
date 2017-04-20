package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.DBService.DBServiceAccount;
import no.westerdals.pj3100g15.DBService.DBServiceRecurringTransfer;
import no.westerdals.pj3100g15.ORM.RecurringTransfer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RequestMapRecurringTransfers {

    /**
     * This request map runs a method that gets all recurring transfers for an account
     * @param accountNumber is the account number for an account
     * @return a List<> with all recurring transfers for a specified account
     */
    @RequestMapping(value = "/{accountNumber}/recurringtransfers/account")
    @ResponseBody
    public List<RecurringTransfer> getAllRecurringTransfersForAccount(
            @PathVariable(value = "accountNumber") String accountNumber) {
        List<RecurringTransfer> recurringTransfers = DBServiceRecurringTransfer.getAllRecurringTransfersForAccount(accountNumber);
        //DBServiceConnection.closeConnection();
        return recurringTransfers;
    }

    /**
     * This request map runs a method that gets all recurring transfers from all accounts connected to a customer
     * @param customerId is a unique ID for a customer
     * @return a List<> with all recurring transfers for a specified customer
     */
    @RequestMapping(value = "/{customerId}/recurringtransfers/user")
    @ResponseBody
    public List<RecurringTransfer> getAllRecurringTransfersForUser(
            @PathVariable(value = "customerId") int customerId) {
        List<String> accountNumbers = DBServiceAccount
                .getCustomerAccounts(customerId).stream()
                .map(account -> account.getAccountNumber())
                .collect(Collectors.toList());

        List<RecurringTransfer> recurringTransfers = new ArrayList<>();
        for (String accountNumber : accountNumbers) {
            recurringTransfers.addAll(DBServiceRecurringTransfer.getAllRecurringTransfersForAccount(accountNumber));
        }
        //DBServiceConnection.closeConnection();
        return recurringTransfers;
    }
}
