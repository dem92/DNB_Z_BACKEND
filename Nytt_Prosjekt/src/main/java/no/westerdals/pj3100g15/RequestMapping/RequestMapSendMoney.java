package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.DBService.DBServiceLoggedTransaction;
import no.westerdals.pj3100g15.DBService.DBServiceRecurringTransfer;
import no.westerdals.pj3100g15.DBService.DBServiceSendMoney;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigInteger;

@Controller
public class RequestMapSendMoney {

    /**
     * This request map runs a method for sending money
     * @param message is a string with either a message or a KID-number
     * @param accountNumber is the account number for the senders account
     * @param accountNumber2 is the account number for the recievers account
     * @param kroner is the amount (kroner) being sent
     * @param oere is the amount (øre) being sent
     * @param recurring is a boolean to specify if it's a recurring transfer (true) or just a one-time-transfer (false)
     * @param interval if the payment is a recurring transfer you can specify how often it should occur
     * @param endDate if the payment is a recurring transfer you can specify the date for when it should stop
     * @return a boolean to see if the transfer was successful
     */
    @RequestMapping(value = "/sendmoney/{message}/{sendersAccount}/{recieversAccount}/{kroner}/{oere}/{recurring}/{interval}/{endDate}", method = RequestMethod.GET)
    @ResponseBody
    public boolean sendMoney(
            @PathVariable(value = "message") String message,
            @PathVariable(value = "sendersAccount") String accountNumber,
            @PathVariable(value = "recieversAccount") String accountNumber2,
            @PathVariable(value = "kroner") BigInteger kroner,
            @PathVariable(value = "oere") int oere,
            @PathVariable(value = "recurring") boolean recurring,
            @PathVariable(value = "interval") String interval,
            @PathVariable(value = "endDate") long endDate
    ) {
        if (DBServiceSendMoney.sendMoneyBetweenAccounts(accountNumber, accountNumber2, kroner, oere)) {
            DBServiceLoggedTransaction.logTransfer(accountNumber, accountNumber2, kroner, oere, message);

            if (recurring)
                DBServiceRecurringTransfer.addRecurringTransfer(accountNumber, accountNumber2, kroner, oere, message, interval, endDate);
            return true;
        } else {
            return false;
        }
    }
}
