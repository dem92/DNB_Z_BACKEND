package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.math.BigInteger;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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
            WriteLogg.writeLogg(e);
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
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    public static List<Account> getCustomerAccounts(int customerId) {
        makeConnection();

        try {
            Dao<Customer, String> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            List<Customer> customer = customerDao.queryForEq("Kundenummer", customerId);

            if (customer.size() != 1)
                return null;

            Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
            List<Account> accounts = accountDao.queryForEq("Kundenummer", customer.get(0).getCustomerID());

            return accounts;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
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
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    public static String[] getPassword(String birthdayNumber) {
        makeConnection();

        try {
            Dao<UserPassword, String> passwordDao = DaoManager.createDao(connectionSource, UserPassword.class);
            List<UserPassword> password = passwordDao.queryForEq("Foedselsnummer", birthdayNumber);

            if (password.size() != 1)
                return null;

            return new String[]{password.get(0).getPasswordHash(), password.get(0).getPasswordPlain()};
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
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
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    public static Customer getCustomer(int customerId) {
        makeConnection();

        try {
            Dao<Customer, String> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            List<Customer> customer = customerDao.queryForEq("Kundenummer", customerId);

            if (customer.size() != 1)
                return null;

            return customer.get(0);
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }


    public static boolean addAccount(int customerId, String accountType) {
        makeConnection();

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

        if(getCustomerAccounts(customerId).size() > 1){
            account.setMain(1);
        }else {account.setMain(0);}


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
            Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
            accountDao.create(account);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }


    public static boolean sendMoney(String accountNumber, String accountNumber2, BigInteger kroner, int oere) {
        makeConnection();
        Account sending = getAccount(accountNumber);
        Account recieving = getAccount(accountNumber2);
        boolean hasMoney = false;

        if(oere>99){
            return false;
        }

        if (sending.getOere() < oere) {
            sending.setKroner(sending.getKroner().subtract((BigInteger.ONE)));
            sending.setOere(sending.getOere() + 100);
        }
        if (sending.getKroner().subtract(kroner).compareTo(BigInteger.ZERO) >= 0) {
            hasMoney = true;
        }
        if (hasMoney) {
            sending.setOere(sending.getOere() - oere);
            sending.setKroner(sending.getKroner().subtract(kroner));
            recieving.setKroner(recieving.getKroner().add(kroner));
            recieving.setOere(recieving.getOere() + oere);
            if (recieving.getOere() > 99) {
                recieving.setKroner(recieving.getKroner().add(BigInteger.ONE));
                recieving.setOere(recieving.getOere() - 100);
            }
            try {
                Dao<Account, String> accountDao = DaoManager.createDao(connectionSource, Account.class);
                accountDao.update(sending);
                accountDao.update(recieving);
                return true;
            } catch (SQLException e) {
                e.printStackTrace();
                WriteLogg.writeLogg(e);
            }
        } else {
            sending.setKroner(sending.getKroner().add((BigInteger.ONE)));
            sending.setOere(sending.getOere() - 100);
        }
        return false;
    }

    public static List<LoggedTransaction> getPaymentsFromAccount(String accountNumber) {
        makeConnection();

        try {
            Dao<LoggedTransaction, String> transactionDao = DaoManager.createDao(connectionSource, LoggedTransaction.class);
            List<LoggedTransaction> transactions = transactionDao.queryForEq("Avsenderkonto", accountNumber);
            transactions.addAll(transactionDao.queryForEq("Mottakerkonto", accountNumber));
            transactions.stream().sorted(Comparator.comparing(LoggedTransaction::getId)).collect(Collectors.toList());
            Collections.reverse(transactions);
            return transactions;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }

    //TODO oppdater denne metoden. Endre hardkodet verdi i Customer(customerId)
    // TODO bruk heller en if/else og sjekk om brukeren finnes eller ikke. Sett verdien til "NULL" om den ikke finnes
    //TODO lag lang url for springrequestmapcontroller addCustomer-metoden

    public static boolean addCustomer(String firstName, String surname, String birthDayNumber, String email) {
        makeConnection();
        Customer customer = new Customer();

        customer.setFirstName(firstName);
        customer.setSurName(surname);
        customer.setBirthdayNumber(birthDayNumber);
        customer.seteMail(email);
        customer.setPostalCode(9999);
        customer.setPhoneNumber(99999999);
        customer.setScore(0);
        customer.setAddress("Ingen Verdi");
        try {
            Dao<Customer, Integer> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            customerDao.create(customer);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
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
            WriteLogg.writeLogg(e);
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
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    public static boolean updateMain(String accountNumber, int main){
        makeConnection();
        Account account = getAccount(accountNumber);
        account.setMain(main);
        try{
            Dao<Account, String> accountStringDao = DaoManager.createDao(connectionSource, Account.class);
            accountStringDao.update(account);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    public static boolean updateAccountname(String accountNumber, String accountName){
        makeConnection();
        Account account = getAccount(accountNumber);
        account.setName(accountName);
        try{
            Dao<Account, String> accountStringDao = DaoManager.createDao(connectionSource, Account.class);
            accountStringDao.update(account);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    public static boolean updateFirstname(int customerId, String firstname){
        makeConnection();
        Customer customer = getCustomer(customerId);
        customer.setFirstName(firstname);
        try{
            Dao<Customer, Integer> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            customerDao.update(customer);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    public static boolean updateSurname(int customerId, String surname){
        makeConnection();
        Customer customer = getCustomer(customerId);
        customer.setSurName(surname);
        try{
            Dao<Customer, Integer> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            customerDao.update(customer);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    public static boolean updateAddress(int customerId, String address){
        makeConnection();
        Customer customer = getCustomer(customerId);
        customer.setAddress(address);
        try{
            Dao<Customer, Integer> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            customerDao.update(customer);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    public static boolean updatePostalcode(int customerId, int postalcode){
        makeConnection();
        Customer customer = getCustomer(customerId);
        customer.setPostalCode(postalcode);
        try{
            Dao<Customer, Integer> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            customerDao.update(customer);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    public static boolean updateEmail(int customerId, String mail){
        makeConnection();
        Customer customer = getCustomer(customerId);
        customer.seteMail(mail);
        try{
            Dao<Customer, Integer> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            customerDao.update(customer);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    public static boolean updatePhone(int customerId, int phoneNumber){
        makeConnection();
        Customer customer = getCustomer(customerId);
        customer.setPhoneNumber(phoneNumber);
        try{
            Dao<Customer, Integer> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            customerDao.update(customer);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    public static boolean deleteUser(int customerId){
        makeConnection();
        Customer customer = getCustomer(customerId);
        try{
            Dao<Customer, Integer> customerDao = DaoManager.createDao(connectionSource, Customer.class);
            customerDao.delete(customer);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteAccount(String accountNumber) {
        makeConnection();
        Account account = getAccount(accountNumber);
        try {
            Dao<Account, String> accountStringDao = DaoManager.createDao(connectionSource, Account.class);
            accountStringDao.delete(account);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }
}
