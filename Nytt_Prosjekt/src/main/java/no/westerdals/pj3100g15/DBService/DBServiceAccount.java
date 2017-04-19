package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.Account;
import no.westerdals.pj3100g15.ORM.Customer;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class DBServiceAccount {

    /**
     *
     * This method is used to get the balance of an account.
     *
     * @param accountNumber is a accountnumber pointing to a specific account.
     * @return an array containing the kroner-value of the account on place 0 and the amount of oere in place 1 in the array.
     */
    public static BigInteger[] getAccountBalance(String accountNumber) {
        DBServiceConnection.makeConnection();
        try {
            Dao<Account, String> accountDao = DaoManager.createDao(DBServiceConnection.connectionSource, Account.class);

            Account account = accountDao.queryForId(accountNumber);
            // Creates an array to hold the balance,
            // where index 0 holds kroner and index 1 holds øre.
            BigInteger[] balance = new BigInteger[2];
            balance[0] = account.getKroner();
            balance[1] = BigInteger.valueOf(account.getOere());

            return balance;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     *This method creates a DAO-object to the Customer-table in the database. It uses that connection to query for a list customers.
     * Another DAO-object is created to the account-class.
     * It then uses the first object in the customer-list to query for all of its accounts.
     *
     * @param customerId The specific integer for a customer in the database.
     * @return a list containing all of the accounts that the customer has.
     */
    public static List<Account> getCustomerAccounts(int customerId) {
        DBServiceConnection.makeConnection();

        try {
            Dao<Customer, Integer> customerDao = DaoManager.createDao(DBServiceConnection.connectionSource, Customer.class);
            List<Customer> customer = customerDao.queryForEq("Kundenummer", customerId);

            if (customer.size() != 1)
                return null;

            Dao<Account, String> accountDao = DaoManager.createDao(DBServiceConnection.connectionSource, Account.class);
            List<Account> accounts = accountDao.queryForEq("Kundenummer", customer.get(0).getCustomerID());

            return accounts;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param accountNumber The accountnumber is needed to get the account-object with the specified accountnumber.
     * @return Account-object with specified accountnumber.
     */
    public static Account getAccount(String accountNumber) {
        DBServiceConnection.makeConnection();

        try {
            Dao<Account, String> accountDao = DaoManager.createDao(DBServiceConnection.connectionSource, Account.class);
            Account account = accountDao.queryForId(accountNumber);

            return account;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @return a list of all the accounts in the database.
     */
    public static List<Account> getAllAccounts() {
        DBServiceConnection.makeConnection();

        try {
            Dao<Account, String> accountDao = DaoManager.createDao(DBServiceConnection.connectionSource, Account.class);
            List<Account> accounts = accountDao.queryForAll();
            return accounts;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    /**
     * This method creates an account associated with an already existing user in the database.
     * It utilises the randomNumber()-method to create a random accountnumber.
     * It also checks if the accountnumber is available or not.
     * It determines the interest of the account based on what type of account it is.
     * If the account is a "Brukskonto", the account is set as a "main" account.
     * This should also be set by the users existing accounts, but it was to timeconsuming to implement.
     *
     * @param customerId is needed to associate the new account to the customer.
     * @param accountType This sets the type of the account. It is usually "sparekonto" or "brukskonto".
     *                    It determines the rent on the account and also the rules applying to it.
     * @return It returns a boolean (true/false), so that the user can know if the account is succesfully mad or not.
     */
    public static boolean addAccount(int customerId, String accountType) {
        DBServiceConnection.makeConnection();

        Account account = new Account();
        accountType = accountType.toLowerCase();
        String randomNumber = randomNumber();
        account.setCustomerNumber(customerId);
        account.setKroner(BigInteger.ZERO);
        account.setOere(0);
        if (!checkAccount(randomNumber)) {
            account.setAccountNumber(randomNumber);
        } else {
            String randomAccountNumber = randomNumber();
            account.setAccountNumber(randomAccountNumber);
        }

        if (getCustomerAccounts(customerId).size() > 1) {
            account.setMain(1);
        } else {
            account.setMain(0);
        }


        if (accountType.equals("sparekonto")) {
            account.setAccountType("Sparekonto");
            account.setInterest(4.0); //Setter 4 prosent rente for sparekonto
            account.setMain(1);
        } else {
            account.setInterest(2.5); //Setter 2.5 prosent rente for brukskonto
            account.setMain(0);
            account.setAccountType("Brukskonto");
        }
        try {
            Dao<Account, String> accountDao = DaoManager.createDao(DBServiceConnection.connectionSource, Account.class);
            accountDao.create(account);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * This method is used to generate random accountnumbers.
     *
     * @return a number created of two random numbers with values between 100000 and 999999
     */
    public static String randomNumber() {
        String accountNo;
        int first = ThreadLocalRandom.current().nextInt(100000, 999999);
        String frst = Integer.toString(first);
        int last = ThreadLocalRandom.current().nextInt(10000, 99999);
        String lst = Integer.toString(last);
        accountNo = frst + lst;
        return accountNo;
    }

    /**
     * Returns true/false based on if the account with the specified accountnumber exists or not.
     *
     * @param accountNumber is used to get the account with the specified accountnumber.
     * @return boolean
     */
    public static boolean checkAccount(String accountNumber) {
        DBServiceConnection.makeConnection();
        try {
            Dao<Account, String> accountStringDao = DaoManager.createDao(DBServiceConnection.connectionSource, Account.class);
            if (accountStringDao.idExists(accountNumber)) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    /**
     *
     * Used to update the status of the account.
     * The account can be 0(main account) or 1(not main account)
     *
     * @param accountNumber is used to get the account in DB with the specified accountnumber.
     * @param main indicates if the account is the main account of the user.
     * @return boolean
     */
    public static boolean updateMain(String accountNumber, int main) {
        DBServiceConnection.makeConnection();
        Account account = getAccount(accountNumber);
        account.setMain(main);
        return updateAccount(account);
    }

    /**
     *
     * Is used to update the name of the account.
     *
     * @param accountNumber makes it possible to get the account-object related to the accountnumber.
     * @param accountName the new name of the account.
     * @return boolean
     */
    public static boolean updateAccountname(String accountNumber, String accountName) {
        DBServiceConnection.makeConnection();
        Account account = getAccount(accountNumber);
        account.setName(accountName);
        return updateAccount(account);
    }

    /**
     * Updates an account-object in the database
     *
     * @param account the updated account to be saved in the database.
     * @return boolean
     */
    public static boolean updateAccount(Account account) {
        try {
            Dao<Account, String> accountDao = DaoManager.createDao(DBServiceConnection.connectionSource, Account.class);
            accountDao.update(account);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    /**
     *
     * Subtracts the given amount from the account-object and returns the accountobject.
     *
     * @param account the account object which is going to be changed.
     * @param kroner amount of kroner the account will have less of after the transaction.
     * @param oere amount of oere the account will have less of.
     * @return Account
     */
    public static Account subtractFromAccount(Account account, BigInteger kroner, int oere) {
        account.setKroner(account.getKroner().subtract(kroner));
        account.setOere(account.getOere() - oere);
        return account;
    }

    /**
     *
     *
     *
     * @param account
     * @param kroner
     * @param oere
     * @return
     */
    public static Account addToAccount(Account account, BigInteger kroner, int oere) {
        account.setOere(account.getOere() + oere);

        if (account.getOere() + oere > 99) {
            kroner.add(BigInteger.ONE);
            account.setOere(account.getOere() + oere - 100);
        } else {
            account.setOere(oere + account.getOere());
        }
        account.setKroner(account.getKroner().add(kroner));
        return account;
    }

    /**
     *
     * @param accountNumber
     * @return
     */
    public static boolean deleteAccount(String accountNumber) {
        DBServiceConnection.makeConnection();
        Account account = getAccount(accountNumber);
        try {
            Dao<Account, String> accountStringDao = DaoManager.createDao(DBServiceConnection.connectionSource, Account.class);
            accountStringDao.delete(account);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * @param account
     * @param oere
     * @return
     */
    public static boolean lessOereOnAccountThanSentOere(Account account, int oere) {
        if (account.getOere() < oere) {
            return true;
        }
        return false;
    }

    /**
     *
     * @param account
     * @return
     */
    public static Account subtractOneKroneAndAdd100OereToAccount(Account account) {
        account.setKroner(account.getKroner().subtract(BigInteger.ONE));
        account.setOere(account.getOere() + 100);
        return account;
    }
}
