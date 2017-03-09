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
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer getCustomer(@PathVariable(value = "id") int customerId) {
        Customer customer = DBServiceCustomer.getCustomer(customerId);
        //DBServiceConnection.closeConnection();
        return customer;
    }

    @RequestMapping(value = "/user/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getAllCustomers() {
        List<Customer> customers = DBServiceCustomer.getAllCustomers();
        //DBServiceConnection.closeConnection();
        return customers;
    }

    @RequestMapping(value = "/newuser/{firstname}/{surname}/{email}/{birthdaynumber}", method = RequestMethod.GET)
    @ResponseBody
    public boolean createNewUser(
            @PathVariable(value = "firstname") String firstName,
            @PathVariable(value = "surname") String surName,
            @PathVariable(value = "birthdaynumber") String birthdaynumber,
            @PathVariable(value = "email") String email) {
        if (DBServiceCustomer.addCustomer(firstName, surName, birthdaynumber, email)) {
            //DBServiceConnection.closeConnection();
            return true;
        }
        DBServiceConnection.closeConnection();
        return false;
    }

    // -------------------    Oppdatere customer under denne streken   ---------------------

    @RequestMapping(value = "/updateuser/{customerid}/surname/{surname}")
    @ResponseBody
    public boolean updateCustomerSurname(@PathVariable(value = "customerid") int customerId,
                                         @PathVariable(value = "surname") String surname) {

        if (DBServiceCustomer.updateSurname(customerId, surname)) {
            //DBServiceConnection.closeConnection();
            return true;
        } else {
            //DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/firstname/{firstname}")
    @ResponseBody
    public boolean updateCustomerFirstName(@PathVariable(value = "customerid") int customerId,
                                           @PathVariable(value = "firstname") String firstName) {

        if (DBServiceCustomer.updateFirstname(customerId, firstName)) {
            //DBServiceConnection.closeConnection();
            return true;
        } else {
            //DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/address/{address}")
    @ResponseBody
    public boolean updateCustomerAddress(@PathVariable(value = "customerid") int customerId,
                                         @PathVariable(value = "address") String address) {

        if (DBServiceCustomer.updateAddress(customerId, address)) {
            //DBServiceConnection.closeConnection();
            return true;
        } else {
            //DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/postalcode/{postalcode}")
    @ResponseBody
    public boolean updateCustomerPostalCode(@PathVariable(value = "customerid") int customerId,
                                            @PathVariable(value = "postalcode") int postalCode) {

        if (DBServiceCustomer.updatePostalcode(customerId, postalCode)) {
            //DBServiceConnection.closeConnection();
            return true;
        } else {
            //DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/email/{email}")
    @ResponseBody
    public boolean updateCustomerEmail(@PathVariable(value = "customerid") int customerId,
                                       @PathVariable(value = "email") String email) {

        if (DBServiceCustomer.updateEmail(customerId, email)) {
            //DBServiceConnection.closeConnection();
            return true;
        } else {
            //DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/updateuser/{customerid}/phonenumber/{phone}")
    @ResponseBody
    public boolean updateCustomerPhone(@PathVariable(value = "customerid") int customerId,
                                       @PathVariable(value = "phone") int phone) {

        if (DBServiceCustomer.updatePhone(customerId, phone)) {
            //DBServiceConnection.closeConnection();
            return true;
        } else {
            //DBServiceConnection.closeConnection();
            return false;
        }
    }

    @RequestMapping(value = "/deleteuser/{customerid}")
    @ResponseBody
    public boolean deleteCustomer(@PathVariable(value = "customerid") int customerId) {
        if (DBServiceCustomer.deleteUser(customerId)) {
            //DBServiceConnection.closeConnection();
            return true;
        }
        //DBServiceConnection.closeConnection();
        return false;
    }
}
