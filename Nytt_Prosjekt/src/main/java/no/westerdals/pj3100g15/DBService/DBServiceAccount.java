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
     * @param accountNumber
     * @return
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
     *
     * @param customerId
     * @return
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
     * @param accountNumber
     * @return
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
     * @return
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
     *
     * @param customerId
     * @param accountType
     * @return
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
     * @return String
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
     *
     * @param accountNumber
     * @return
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
     * @param accountNumber
     * @param main
     * @return
     */
    public static boolean updateMain(String accountNumber, int main) {
        DBServiceConnection.makeConnection();
        Account account = getAccount(accountNumber);
        account.setMain(main);
        try {
            Dao<Account, String> accountStringDao = DaoManager.createDao(DBServiceConnection.connectionSource, Account.class);
            accountStringDao.update(account);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    /**
     *
     * @param accountNumber
     * @param accountName
     * @return
     */
    public static boolean updateAccountname(String accountNumber, String accountName) {
        DBServiceConnection.makeConnection();
        Account account = getAccount(accountNumber);
        account.setName(accountName);
        try {
            Dao<Account, String> accountStringDao = DaoManager.createDao(DBServiceConnection.connectionSource, Account.class);
            accountStringDao.update(account);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    /**
     *
     * @param account
     * @return
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
     * @param account
     * @param kroner
     * @param oere
     * @return
     */
    public static Account subtractFromAccount(Account account, BigInteger kroner, int oere) {
        account.setKroner(account.getKroner().subtract(kroner));
        account.setOere(account.getOere() - oere);
        return account;
    }

    /**
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
