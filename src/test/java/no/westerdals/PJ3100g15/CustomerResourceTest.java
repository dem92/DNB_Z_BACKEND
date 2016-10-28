package no.westerdals.PJ3100g15;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerResourceTest {
    private static final String UNKNOWN_ID = "-1";
    private static final String KNOWN_ID = "1286608621";

    private final Database database = mock(Database.class);
    private final CustomerResource customerResource = new CustomerResource(database);

    @Before
    public void setUp() throws Exception {
        when(database.getAllCustomers()).thenReturn(Arrays.asList(
                new Customer(KNOWN_ID, "hei", "der", "blah", 1234, "hello@gmail.com", 12345687, 123),
                new Customer("54321", "hei", "der", "blah", 1234, "hello@gmail.com", 12345687, 123)));
    }

    @Test
    public void getCustomerByIdReturnsCustomer() throws Exception {
        // when
        final Customer customer = customerResource.getCustomerById(KNOWN_ID);

        // then
        Assert.assertEquals(KNOWN_ID, customer.getFoedselsnummer());
    }

    @Test(expected = CustomerNotFoundException.class)
    public void getCustomerByIdThrowsWhenNotFound() throws Exception {
        customerResource.getCustomerById(UNKNOWN_ID);
    }
}