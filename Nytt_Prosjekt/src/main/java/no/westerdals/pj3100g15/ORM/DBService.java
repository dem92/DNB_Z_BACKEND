package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Eva Dahlo on 23/11/2016.
 */
public class DBService {
    private static ConnectionSource connectionSource;

    //Should be called first thing in every method in this class that requires a working connection to the database.
    private static void makeConnection() {
        if (connectionSource == null)
            connectionSource = DBConnector.makeConnection();
    }

    public static List<Customer> getAllCustomers() {
        makeConnection();

        try {
            Dao<Customer, String> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            List<Customer> customers = customerDao.queryForAll();
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BigInteger[] getAccountBalance(String accountNumber) {
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

    public static List<Account> getCustomerAccounts(String birthNumber) {
        makeConnection();

        try {
            Dao<Customer, String> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            List<Customer> customer = customerDao.queryForEq("Foedselsnummer", birthNumber);

            if (customer.size() != 1)
                return null;

            Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
            List<Account> accounts = accountDao.queryForEq("Kundenummer", customer.get(0).getCustomerID());

            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Account getAccount(String accountNumber) {
        makeConnection();

        try {
            Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
            Account account = accountDao.queryForId(accountNumber);

            return account;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String[] getPassword(String birthNumber) {
        makeConnection();

        try {
            Dao<UserPassword, String> passwordDao = DaoManager.createDao(connectionSource, UserPassword.class);
            List<UserPassword> password = passwordDao.queryForEq("Foedselsnummer", birthNumber);

            if (password.size() != 1)
                return null;

            return new String[]{password.get(0).getPasswordHash(), password.get(0).getPasswordPlain()};
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Account> getAllAccounts() {
        makeConnection();

        try {
            Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
            List<Account> accounts = accountDao.queryForAll();
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Customer getCustomer(String birthNumber) {
        makeConnection();

        try {
            Dao<Customer, String> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            List<Customer> customer = customerDao.queryForEq("Foedselsnummer", birthNumber);

            if (customer.size() != 1)
                return null;

            return customer.get(0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void addOrUpdateAccount(int customerId, int accountType, String accountNumber, BigInteger kroner, int oere, int main) {
        makeConnection();

        Account account = new Account();
        account.setCustomerNumber(customerId);
        account.setAccountType(accountType);
        account.setKroner(kroner);
        account.setOere(oere);
        if (checkAccount(accountNumber)){
            account.setAccountNumber(accountNumber);
        }else {
            String randomAccountNumber = randomNumber();
            account.setAccountNumber(randomAccountNumber);
        }
        account.setMain(main);
        if (accountType == 2) {
            account.setInterest(4.0); //Setter 4 prosent rente for sparekonto
            account.setMain(1);
        } else {
            account.setInterest(2.5); //Setter 2.5 prosent rente for brukskonto
            account.setMain(0);
        }
        try {
            Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
            accountDao.createOrUpdate(account);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //TODO oppdater denne metoden. Endre hardkodet verdi i Customer(customerId)
    // TODO bruk heller en if/else og sjekk om brukeren finnes eller ikke. Sett verdien til "NULL" om den ikke finnes
    //TODO lag lang url for springrequestmapcontroller addCustomer-metoden

    public static void addOrUpdateCustomer(String firstName, String surname, String birthDayNumber, String email, int customerId, String address, int postalCode, int telephoneNumber) {
        makeConnection();
        Customer customer = new Customer();
        if (checkCustomer(customerId)) {
            customer.setCustomerID(customerId);
        }
        customer.setFirstName(firstName);
        customer.setSurName(surname);
        customer.setPostalCode(postalCode);
        customer.setPhoneNumber(telephoneNumber);
        customer.setAddress(address);
        customer.setBirthdayNumber(birthDayNumber);
        customer.seteMail(email);
        customer.setScore(0);
        try {
            Dao<Customer, Integer> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            customerDao.createOrUpdate(customer); //
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String randomNumber() {
        String accountNo;
        int first = ThreadLocalRandom.current().nextInt(100000, 999999);
        String frst = Integer.toString(first);
        int last = ThreadLocalRandom.current().nextInt(10000, 99999);
        String lst = Integer.toString(last);
        accountNo = frst + lst;
        return accountNo;
    }


    public static boolean checkCustomer(int customerId) {
        makeConnection();
        try {
            Dao<Customer, Integer> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            if (customerDao.idExists(customerId)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean checkAccount(String accountNumber) {
        makeConnection();
        try {
            Dao<Account, String> accountStringDao = DaoManager.createDao(connectionSource, Account.class);
            if (accountStringDao.idExists(accountNumber)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
