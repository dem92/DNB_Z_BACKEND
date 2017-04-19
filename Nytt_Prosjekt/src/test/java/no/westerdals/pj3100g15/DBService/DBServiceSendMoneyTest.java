package no.westerdals.pj3100g15.DBService;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DBServiceSendMoneyTest {

    @Test
    public void testSendMoneyFromAccountToSavingsTarget(){
        assertTrue(DBServiceSendMoney.sendMoneyFromAccountToSavingsTarget("63454037373", 100, BigInteger.valueOf(5),20));
        assertTrue(DBServiceSendMoney.sendMoneyFromAccountToSavingsTarget("63454037373", 100, BigInteger.valueOf(5),0));
    }

    @Test
    public void testSendMoneyFromSavingsTargetToAccount(){
        assertTrue(DBServiceSendMoney.sendMoneyFromSavingsTargetToAccount("63454037373", 100, BigInteger.valueOf(5),20));
        assertTrue(DBServiceSendMoney.sendMoneyFromSavingsTargetToAccount("63454037373", 100, BigInteger.valueOf(5),0));
    }


}
