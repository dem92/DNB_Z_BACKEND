package no.westerdals.pj3100g15.ORM;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;


public class DBServiceUserTest {
    @Test
    public void getAllCustomers() throws Exception {
        List<Customer> customers = DBService.getAllCustomers();
        assertNotNull(customers);
        assertNotNull(customers.get(0));
    }

    @Test
    public void getCustomerAccounts() throws Exception {
        List<Account> accounts = DBService.getCustomerAccounts(0); // Has to be a valid birthNumber
        assertNotNull(accounts);
        assertNotNull(accounts.get(0));
    }
    @Test
    public void getCustomer() throws Exception {
        Customer customer = DBService.getCustomer(0);
        assertNotNull(customer);
    }
}
