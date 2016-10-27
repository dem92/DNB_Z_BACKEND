package no.westerdals.PJ3100g15;

import java.util.HashMap;

/**
 * Created by Eva Dahlo on 27/10/2016.
 */
public class CustomerController {
    private HashMap<Integer, Customer> customers;

    public CustomerController(){
        customers = new HashMap<Integer, Customer>();
    }

    public boolean addCustomer(int customerID, Customer customer){
        if (customers.containsKey(customerID))
            return false;

        customers.put(customerID, customer);
        return true;
    }

    public boolean containsCustomer(int customerID){
        return customers.containsKey(customerID);
    }

    public int getNumberOfCustomers(){
        return customers.size();
    }
}
