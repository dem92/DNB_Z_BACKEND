package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.DBService.DBServiceConnection;
import no.westerdals.pj3100g15.DBService.DBServiceLoggedTransaction;
import no.westerdals.pj3100g15.ORM.LoggedTransaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RequestMapLoggedTransaction {

    @RequestMapping(value = "/{accountNumber}/transfer")
    @ResponseBody
    public List<LoggedTransaction> getTransferFromAccount(
            @PathVariable(value = "accountNumber") String accountNumber
    ) {
        List<LoggedTransaction> transfers = DBServiceLoggedTransaction.getAllLoggedTransactionsFromAccount(accountNumber).stream()
                .filter(payment -> payment.getTransactionType().equals("transfer"))
                .sorted(Comparator.comparing(LoggedTransaction::getId))
                .collect(Collectors.toList());
        Collections.reverse(transfers);
        //DBServiceConnection.closeConnection();
        return transfers;
    }

    @RequestMapping(value = "/{accountNumber}/card")
    @ResponseBody
    public List<LoggedTransaction> getCardFromAccount(
            @PathVariable(value = "accountNumber") String accountNumber
    ) {
        List<LoggedTransaction> cards = DBServiceLoggedTransaction.getAllLoggedTransactionsFromAccount(accountNumber).stream()
                .filter(payment -> payment.getTransactionType().equals("card"))
                .sorted(Comparator.comparing(LoggedTransaction::getId))
                .collect(Collectors.toList());
        Collections.reverse(cards);
        //DBServiceConnection.closeConnection();
        return cards;
    }


    @RequestMapping(value = "/{accountNumber}/payment")
    @ResponseBody
    public List<LoggedTransaction> getPaymentFromAccount(
            @PathVariable(value = "accountNumber") String accountNumber
    ) {
        List<LoggedTransaction> payments = DBServiceLoggedTransaction.getAllLoggedTransactionsFromAccount(accountNumber).stream()
                .filter(payment -> payment.getTransactionType().equals("payment"))
                .sorted(Comparator.comparing(LoggedTransaction::getId))
                .collect(Collectors.toList());
        Collections.reverse(payments);
        //DBServiceConnection.closeConnection();
        return payments;
    }

    @RequestMapping(value = "/{accountNumber}/all", method = RequestMethod.GET)
    @ResponseBody
    public List<LoggedTransaction> getAllLoggedTransactionsFromAccount(
            @PathVariable(value = "accountNumber") String accountNumber) {
        List<LoggedTransaction> transactions = DBServiceLoggedTransaction.getAllLoggedTransactionsFromAccount(accountNumber).stream()
                .sorted(Comparator.comparing(LoggedTransaction::getId))
                .collect(Collectors.toList());
        Collections.reverse(transactions);
        //DBServiceConnection.closeConnection();
        return transactions;
    }
}
