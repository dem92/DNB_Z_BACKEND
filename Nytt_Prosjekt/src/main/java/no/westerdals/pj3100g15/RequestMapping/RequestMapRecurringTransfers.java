package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.DBService.DBServiceAccount;
import no.westerdals.pj3100g15.DBService.DBServiceConnection;
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

    @RequestMapping(value = "/{accountNumber}/recurringtransfers/account")
    @ResponseBody
    public List<RecurringTransfer> getAllRecurringTransfersForAccount(
            @PathVariable(value = "accountNumber") String accountNumber) {
        List<RecurringTransfer> recurringTransfers = DBServiceRecurringTransfer.getAllRecurringTransfersForAccount(accountNumber);
        //DBServiceConnection.closeConnection();
        return recurringTransfers;
    }

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
