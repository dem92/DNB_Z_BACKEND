package no.westerdals.PJ3100g15;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public class Satan {

    public Satan(){

    }

    public static void main(String[] args){

    }

    public void addCustomer(String id, String firstName, String lastName, String addressLine1, String addressLine2, int postalCode, String eMailAddress, int phoneNumber){
        new Customer(id, firstName, lastName, addressLine1, addressLine2, postalCode, eMailAddress, phoneNumber);
    }
}
