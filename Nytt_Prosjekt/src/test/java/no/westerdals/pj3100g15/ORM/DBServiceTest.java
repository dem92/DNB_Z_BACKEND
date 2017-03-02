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
    public void getAllCustomers() throws Exception {
        List<Customer> customers = DBService.getAllCustomers();
        assertNotNull(customers);
        assertNotNull(customers.get(0));
    }

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

    @Test
    public void getCustomerAccounts() throws Exception {
        List<Account> accounts = DBService.getCustomerAccounts(0); // Has to be a valid birthNumber
        assertNotNull(accounts);
        assertNotNull(accounts.get(0));
    }

    @Test
    public void getPassword() throws Exception {
        String[] password = DBService.getPassword(1); // Has to be a valid birthNumber
        assertNotNull(password);
        assertNotNull(password[0]);
        assertNotNull(password[1]);
    }

    @Test
    public void getCustomer() throws Exception {
        Customer customer = DBService.getCustomer(2); // Has to be a valid birthNumber
        assertNotNull(customer);
    }
}