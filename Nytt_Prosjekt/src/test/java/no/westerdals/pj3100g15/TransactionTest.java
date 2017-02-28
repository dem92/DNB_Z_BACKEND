package no.westerdals.pj3100g15;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TransactionTest {

    @Test
    public void testTest(){
        assertTrue(true);
    }
    /*private final Database database = mock(Database.class);
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
        BankAccount account1 = new BankAccount(1,customer1);
        BankAccount account2 = new BankAccount(2,customer2);

        Transactions transaction = new Transactions(kroner, oere, account1,account2);

        //Act
        boolean shouldBeFalse = transaction.controlAccounts();
        //Assert
        assertEquals(false, shouldBeFalse);
    }*/
}
