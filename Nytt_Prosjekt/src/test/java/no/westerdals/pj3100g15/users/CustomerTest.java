package no.westerdals.pj3100g15.users;

import no.westerdals.pj3100g15.Account;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Sven Craehn on 23.11.2016.
 */
public class CustomerTest {

    Customer customer = new Customer("1", "Fornavn", "Etternavn", "Adresse", 1234, "hello@gmail.com", 12345687, 123);
    Account account = new Account(21321, customer);

    @Before
    public void setUp() {
        customer.addAccount(account);
    }

    @Test
    public void testAddAccount(){
        Account account2 = new Account(21321, customer);
        Boolean addAccount = customer.addAccount(account2);

        assertTrue(addAccount);
    }

    @Test
    public void testGetAccounts(){
        int accounts = customer.getAccounts().size();

        assertEquals(1, accounts);
    }
}
