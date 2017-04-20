package no.westerdals.pj3100g15.DBService;

import no.westerdals.pj3100g15.ORM.LoggedTransaction;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static no.westerdals.pj3100g15.DBService.DBServiceLoggedTransaction.sortTransactions;
import static org.junit.Assert.assertEquals;

public class DBServiceLoggedTransactionTest {
    /**
     * Tests if the sortingmethod works...
     */
    @Test
    public void testSortMethod(){
        List<LoggedTransaction> transactions = new ArrayList<>();
        Long timestamp = System.currentTimeMillis() / 1000L;
        transactions.add(0, new LoggedTransaction(1, "77926693162", "89169070246", timestamp+3, BigInteger.valueOf(50), 20,"TheNextBestMessage","someTransaction",100,101));
        transactions.add(1, new LoggedTransaction(2, "77926693162", "89169070246", timestamp+1, BigInteger.valueOf(50), 20,"TheBestMessage","someTransaction",102,103));
        transactions.add(2, new LoggedTransaction(3, "77926693162", "89169070246", timestamp+5, BigInteger.valueOf(50), 20,"TheThirdBestMessage","someTransaction",104,105));
        transactions.add(3, new LoggedTransaction(5, "77926693162", "89169070246", timestamp+7, BigInteger.valueOf(50), 20,"TheFourthBestMethod","someTransaction",106,107));
        transactions.add(3, new LoggedTransaction(6, "77926693162", "89169070246", timestamp+9, BigInteger.valueOf(50), 20,"TheFiftBestMethod","someTransaction",106,107));
        transactions.add(3, new LoggedTransaction(7, "77926693162", "89169070246", timestamp+10, BigInteger.valueOf(50), 20,"TheSixthBestMethod","someTransaction",106,107));
        transactions.add(3, new LoggedTransaction(8, "77926693162", "89169070246", timestamp+15, BigInteger.valueOf(50), 20,"TheSeventhBestMethod","someTransaction",106,107));
        transactions = sortTransactions(transactions);
        for(int i =0; i<transactions.size();i++){
            System.out.println(i + ". "+transactions.get(i).getMessage_kid());

        }
        assertEquals("TheBestMessage", transactions.get(0).getMessage_kid());
    }
}
