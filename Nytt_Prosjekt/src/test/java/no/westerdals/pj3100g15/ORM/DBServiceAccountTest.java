package no.westerdals.pj3100g15.ORM;

import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Henrik on 02.03.2017.
 */
public class DBServiceAccountTest {

    @Test
    public void getAllAccounts() throws Exception {
        List<Account> accounts = DBService.getAllAccounts();
        assertNotNull(accounts);
        assertNotNull(accounts.get(0));
    }

    @Test
    public void getAccount() throws Exception {
        Account account = DBService.getAccount("95207460912"); // Has to be a valid accountNumber
        assertNotNull(account);
    }

    @Test
    public void getAccountBalance() throws Exception {
        BigInteger[] balance = DBService.getAccountBalance("80766645136"); // Has to be a valid accountNumber
        assertNotNull(balance);
        assertNotNull(balance[0]);
        assertNotNull(balance[1]);
    }
}
