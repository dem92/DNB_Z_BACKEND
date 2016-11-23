package no.westerdals.pj3100g15.ORM;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * Created by Eva Dahlo on 23/11/2016.
 */
public class DBServiceTest {
    @Test
    public void getAccountBalance() throws Exception {
        BigInteger[] balance = DBService.getAccountBalance("80766645136"); // Has to be a valid accountNumber
        assertNotNull(balance);
        assertNotNull(balance[0]);
        assertNotNull(balance[1]);
    }

}