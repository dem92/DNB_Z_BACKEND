package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.Customer;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DBServiceCustomer {

    private DBServiceCustomer() {
    }

    /**
     *
     * This method creates a connection to the database and retrieves all customers in the database.
     *
     * @return 'List<Customer>' a list with customers.
     */
    public static List<Customer> getAllCustomers() {
        DBServiceConnection.makeConnection();
        try {
            Dao<Customer, String> customerDao = DaoManager.createDao(DBServiceConnection.connectionSource, Customer.class);
            return customerDao.queryForAll();
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     * Creates/reuses a connection to the database and returns the customer with the specified id.
     *
     * @param customerId is used to get the customer with the specified id.
     * @return Customer
     */
    public static Customer getCustomer(int customerId) {
        DBServiceConnection.makeConnection();

        try {
            Dao<Customer, Integer> customerDao = DaoManager.createDao(DBServiceConnection.connectionSource, Customer.class);
            List<Customer> customer = customerDao.queryForEq("Kundenummer", customerId);

           // if (customer.size() != 1)
           //     return null;

            return customer.get(0);
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return new Customer();
    }

    /**
     *
     * Creates a customerobject and sets all the parameters needed to persist it to the database.
     * It Hardcodes the postalcode, phonenumber, score and address-field,
     * because it is not as important and can be changed at a later point
     * by the user.
     *
     *
     * @param firstName is set as the firstname of the customer-object
     * @param surname is set as the surname for the customer-object
     * @param birthDayNumber is set as the birthdaynumber to the customer-object
     * @param email is set as the email-parameter to the customer-object
     * @return boolean
     */
    public static boolean addCustomer(String firstName, String surname, String birthDayNumber, String email) {
        DBServiceConnection.makeConnection();
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
            Dao<Customer, Integer> customerDao = DaoManager.createDao(DBServiceConnection.connectionSource, Customer.class);
            customerDao.create(customer);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            WriteLogg.writeLogg(e);
        }
        return false;
    }

    /**
     * Utilmethod to persist a customer with changed values.
     * @param customer the object that is changed
     * @return boolean
     */
    private static boolean updateCustomer(Customer customer){
        try {
            Dao<Customer, Integer> customerDao = DaoManager.createDao(DBServiceConnection.connectionSource, Customer.class);
            customerDao.update(customer);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateFirstname(int customerId, String firstname) {
        DBServiceConnection.makeConnection();
        Customer customer = getCustomer(customerId);
        customer.setFirstName(firstname);
        return updateCustomer(customer);
    }

    public static boolean updateSurname(int customerId, String surname) {
        DBServiceConnection.makeConnection();
        Customer customer = getCustomer(customerId);
        customer.setSurName(surname);
        return updateCustomer(customer);
    }

    public static boolean updateAddress(int customerId, String address) {
        DBServiceConnection.makeConnection();
        Customer customer = getCustomer(customerId);
        customer.setAddress(address);
        return updateCustomer(customer);
    }

    public static boolean updatePostalcode(int customerId, int postalcode) {
        DBServiceConnection.makeConnection();
        Customer customer = getCustomer(customerId);
        customer.setPostalCode(postalcode);
        return updateCustomer(customer);
    }

    public static boolean updateEmail(int customerId, String mail) {
        DBServiceConnection.makeConnection();
        Customer customer = getCustomer(customerId);
        customer.seteMail(mail);
        return updateCustomer(customer);
    }

    public static boolean updatePhone(int customerId, int phoneNumber) {
        DBServiceConnection.makeConnection();
        Customer customer = getCustomer(customerId);
        customer.setPhoneNumber(phoneNumber);
        return updateCustomer(customer);
    }

    public static boolean deleteUser(int customerId) {
        DBServiceConnection.makeConnection();
        Customer customer = getCustomer(customerId);
        try {
            Dao<Customer, Integer> customerDao = DaoManager.createDao(DBServiceConnection.connectionSource, Customer.class);
            customerDao.delete(customer);
            return true;
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return false;
    }

    public static boolean checkCustomer(int customerId) {
        DBServiceConnection.makeConnection();
        try {
            Dao<Customer, Integer> customerDao = DaoManager.createDao(DBServiceConnection.connectionSource, Customer.class);
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
}
