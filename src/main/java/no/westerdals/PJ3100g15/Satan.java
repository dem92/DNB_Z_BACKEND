package no.westerdals.PJ3100g15;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public class Satan {

    public Satan(){

    }

    public static void main(String[] args){
        //Dette er noe bullshit for å kunne teste Transactionsklassen hilsen Henrik 26.10.16(onsdag 2.sprint)
        Account Martin = new Account(1,AccountType.CHECKING,5.0);
        Account Fredrik = new Account(2,AccountType.CHECKING,5.0);
        new Transactions(50,20,Martin,Fredrik);
    }

    public void addCustomer(String id, String firstName, String lastName, String addressLine1, String addressLine2, int postalCode, String eMailAddress, int phoneNumber){
        new Customer(id, firstName, lastName, addressLine1, addressLine2, postalCode, eMailAddress, phoneNumber);

    }
}
