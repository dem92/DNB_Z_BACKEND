package no.westerdals.pj3100g15.RequestMapping;

import no.westerdals.pj3100g15.DBService.DBServiceConnection;
import no.westerdals.pj3100g15.DBService.DBServiceCustomer;
import no.westerdals.pj3100g15.ORM.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RequestMapCustomer {

    /**
     * This request map runs a method to get a customer
     * @param customerId is a unique ID for a customer
     * @return a Customer-object
     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomer(@PathVariable(value = "id") int customerId) {
        return DBServiceCustomer.getCustomer(customerId);
    }

    /**
     * This request map runs a method that gets all customers
     * @return a List<> of Customer-objects
     */
    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getAllCustomers() {
        return DBServiceCustomer.getAllCustomers();
    }

    /**
     * This request map runs a method to create a new customer
     * @param firstName is the firstname for the new customer
     * @param surName is the surname for the new customer
     * @param birthdaynumber is the social security number for the new customer
     * @param email is the new customers email adress
     * @return a boolean to see if the customer was successfully created
     */
    @RequestMapping(value = "/newuser/{firstname}/{surname}/{email}/{birthdaynumber}", method = RequestMethod.GET)
    @ResponseBody
    public boolean createNewUser(
            @PathVariable(value = "firstname") String firstName,
            @PathVariable(value = "surname") String surName,
            @PathVariable(value = "birthdaynumber") String birthdaynumber,
            @PathVariable(value = "email") String email) {
        if (DBServiceCustomer.addCustomer(firstName, surName, birthdaynumber, email)) {
            return true;
        }
        DBServiceConnection.closeConnection();
        return false;
    }

    /**
     * This request map runs a method that updates the customers surname
     * @param customerId is a unique ID for the customer
     * @param surname is a string for the new surname
     * @return a boolean to see if the change was successful
     */
    @RequestMapping(value = "/updateuser/{customerid}/surname/{surname}")
    @ResponseBody
    public boolean updateCustomerSurname(@PathVariable(value = "customerid") int customerId,
                                         @PathVariable(value = "surname") String surname) {

        if (DBServiceCustomer.updateSurname(customerId, surname)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This request map runs a method that updates the customers firstname
     * @param customerId is a unique ID for the customer
     * @param firstName is a string for the new firstname
     * @return a boolean to see if the change was successful
     */
    @RequestMapping(value = "/updateuser/{customerid}/firstname/{firstname}")
    @ResponseBody
    public boolean updateCustomerFirstName(@PathVariable(value = "customerid") int customerId,
                                           @PathVariable(value = "firstname") String firstName) {

        if (DBServiceCustomer.updateFirstname(customerId, firstName)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This request map runs a method that updates the customers address
     * @param customerId is a unique ID for the customer
     * @param address is a string for the new address
     * @return a boolean to see if the change was successful
     */
    @RequestMapping(value = "/updateuser/{customerid}/address/{address}")
    @ResponseBody
    public boolean updateCustomerAddress(@PathVariable(value = "customerid") int customerId,
                                         @PathVariable(value = "address") String address) {

        if (DBServiceCustomer.updateAddress(customerId, address)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This request map runs a method that updates the customers postal code
     * @param customerId is a unique ID for the customer
     * @param postalCode is an int for the new postal code
     * @return a boolean to see if the change was successful
     */
    @RequestMapping(value = "/updateuser/{customerid}/postalcode/{postalcode}")
    @ResponseBody
    public boolean updateCustomerPostalCode(@PathVariable(value = "customerid") int customerId,
                                            @PathVariable(value = "postalcode") int postalCode) {

        if (DBServiceCustomer.updatePostalcode(customerId, postalCode)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This request map runs a method that updates the customers email address
     * @param customerId is a unique ID for the customer
     * @param email is a string for the new email address
     * @return a boolean to see if the change was successful
     */
    @RequestMapping(value = "/updateuser/{customerid}/email/{email}")
    @ResponseBody
    public boolean updateCustomerEmail(@PathVariable(value = "customerid") int customerId,
                                       @PathVariable(value = "email") String email) {

        if (DBServiceCustomer.updateEmail(customerId, email)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This request map runs a method that updates the customers phone number
     * @param customerId is a unique ID for the customer
     * @param phone is an int for the new phone number
     * @return a boolean to see if the change was successful
     */
    @RequestMapping(value = "/updateuser/{customerid}/phonenumber/{phone}")
    @ResponseBody
    public boolean updateCustomerPhone(@PathVariable(value = "customerid") int customerId,
                                       @PathVariable(value = "phone") int phone) {

        if (DBServiceCustomer.updatePhone(customerId, phone)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This request map runs a method that deletes a customer from the database
     * @param customerId is a unique ID for the customer
     * @return a boolean to see if the customer was successfully deleted
     */
    @RequestMapping(value = "/deleteuser/{customerid}")
    @ResponseBody
    public boolean deleteCustomer(@PathVariable(value = "customerid") int customerId) {
        if (DBServiceCustomer.deleteUser(customerId)) {
            return true;
        }
        return false;
    }
}
