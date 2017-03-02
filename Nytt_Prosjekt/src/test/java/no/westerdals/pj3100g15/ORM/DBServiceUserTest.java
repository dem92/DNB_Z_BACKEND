package no.westerdals.pj3100g15.ORM;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


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

    @Test
    public void getPassword() throws Exception {
        boolean isNotEmpty = false;
        String[] password = DBService.getPassword("23039818145");
        if (password != null){
            isNotEmpty = true;
        }
        assertTrue(isNotEmpty);
    }

    @Test
    public void addCustomer() throws Exception {
        Customer customer = new Customer();
        boolean couldCreate = false;
        DBService.addCustomer("Fornavn", "Etternavn", "01029912345", "minepost@mail.com");
        customer.setCustomerID(220);
        int customerId = 220;
        if (customer.getCustomerID() == customerId){

        }
        assertTrue(couldCreate);
    }

    @Test
    public void updateFirstname() throws Exception {
        boolean couldChange = DBService.updateFirstname(150, "Nytt namn");
        assertTrue(couldChange);
    }

    @Test
    public void updateSurname() throws Exception {
        boolean couldChange = DBService.updateSurname(150, "Nytt namn");
        assertTrue(couldChange);
    }

    @Test
    public void updateAddress() throws Exception {
        boolean couldChange = DBService.updateAddress(150, "Ny adresse");
        assertTrue(couldChange);
    }

    @Test
    public void updatePostalcode() throws Exception {
        boolean couldChange = DBService.updatePostalcode(150, 1234);
        assertTrue(couldChange);
    }

    @Test
    public void updateEmail() throws Exception {
        boolean couldChange = DBService.updateEmail(150, "minnyamail@mail.com");
        assertTrue(couldChange);
    }

    @Test
    public void updatePhone() throws Exception {
        boolean couldChange = DBService.updatePhone(150, 66666666);
        assertTrue(couldChange);
    }

    @Test
    public void deleteUser() throws Exception {
        boolean couldDelete = DBService.deleteUser(220);
        assertTrue(couldDelete);
    }
}
