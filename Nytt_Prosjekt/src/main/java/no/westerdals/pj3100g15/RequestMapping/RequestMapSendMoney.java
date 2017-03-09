package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.DBService.DBServiceConnection;
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
        if (DBServiceSendMoney.sendMoney(accountNumber, accountNumber2, kroner, oere)) {
            DBServiceLoggedTransaction.logTransfer(accountNumber, accountNumber2, kroner, oere, message);

            if (recurring)
                DBServiceRecurringTransfer.addRecurringTransfer(accountNumber, accountNumber2, kroner, oere, message, interval, endDate);
            DBServiceConnection.closeConnection();
            return true;
        } else {
            DBServiceConnection.closeConnection();
            return false;
        }
    }
}
