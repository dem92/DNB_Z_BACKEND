package no.westerdals.pj3100g15.users;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sven Craehn on 23.11.2016.
 */
public class CustomerControllerTest {

    CustomerController cc = new CustomerController();

    @Before
    public void setUp(){
        for(int i = 0; i < 10; i++) {
            cc.addCustomer(i, new Customer("54321", "hei", "der", "blah", 1234, "hello@gmail.com", 12345687, 123));
        }
    }

    @Test
    public void testNotNullHashMap(){
        assertNotNull(cc.getNumberOfCustomers());
    }

    @Test
    public void testAddCustomer(){
        cc.addCustomer(11, new Customer("54321", "hei", "der", "blah", 1234, "hello@gmail.com", 12345687, 123));
        assertEquals(cc.getNumberOfCustomers(), 11);
    }

    @Test
    public void testHasCustomerObject(){
        boolean customer = cc.containsCustomer(0);

        assertTrue(customer);
    }

    @Test
    public void testHasNotCustomerObject(){
        boolean customer = cc.containsCustomer(15);

        assertFalse(customer);
    }
}
