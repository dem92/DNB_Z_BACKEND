package no.westerdals.pj3100g15.ORM;

import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Eva Dahlo on 23/11/2016.
 */
public class DBServiceTest {
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

    @Test
    public void getCustomerAccounts() throws Exception {
        List<Account> accounts = DBService.getCustomerAccounts("05089622442"); // Has to be a valid birthNumber
        assertNotNull(accounts);
        assertNotNull(accounts.get(0));
    }

}