package no.westerdals.PJ3100g15;

import no.westerdals.PJ3100g15.users.Customer;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TransactionTest {
    private final Database database = mock(Database.class);
    private final CustomerResource customerResource = new CustomerResource(database);

    @Before
    public void setUp() throws Exception {
        when(database.getAllCustomers()).thenReturn(Arrays.asList(
                new Customer("1", "hei", "der", "blah", 1234, "hello@gmail.com", 12345687, 123),
                new Customer("2", "hei", "der", "blah", 1234, "hello@gmail.com", 12345687, 123)));
    }

    //Skriver en test for å sjekke etter om hvis ingen har noe på konto,
    // så skal transaksjonen ikke bli godkjent.
    @Test
    public void testControlAccounts(){
        //Arrange
        int kroner = 1;
        int oere = 1;
        final Customer customer1 = customerResource.getCustomerById("1");
        final Customer customer2 = customerResource.getCustomerById("2");
        Account account1 = new Account(1,customer1);
        Account account2 = new Account(2,customer2);

        Transactions transaction = new Transactions(kroner, oere, account1,account2);

        //Act
        boolean shouldBeFalse = transaction.controlAccounts();
        //Assert
        assertEquals(false, shouldBeFalse);
    }
}
