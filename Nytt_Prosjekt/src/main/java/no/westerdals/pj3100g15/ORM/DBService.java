package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Eva Dahlo on 23/11/2016.
 */
public class DBService {
    private static ConnectionSource connectionSource;

    //Should be called first thing in every method in this class that requires a working connection to the database.
    private static void makeConnection(){
        if (connectionSource == null)
            connectionSource = DBConnector.makeConnection();
    }

    // TODO: This method is possibly redundant, or should return a more specific set of values.
    public static List<Customer> getAllCustomers(){
        makeConnection();

        try {
            Dao<Customer, Integer> customerDao = DaoManager.createDao(connectionSource, Customer.class);

            List<Customer> customers = customerDao.queryForAll();
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BigInteger[] getAccountBalance(String accountNumber){
        makeConnection();

        try {
            Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);

            Account account = accountDao.queryForId(accountNumber);
            // Creates an array to hold the balance,
            // where index 0 holds kroner and index 1 holds øre.
            BigInteger[] balance = new BigInteger[2];
            balance[0] = account.getKroner();
            balance[1] = BigInteger.valueOf(account.getOere());

            return balance;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
