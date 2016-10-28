package no.westerdals.PJ3100g15.users;

import no.westerdals.PJ3100g15.users.User;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public class Administrator extends User {
    public Administrator(String id, String firstName, String lastName, String addressLine1,/* String addressLine2, */int postalCode, String eMailAddress, int phoneNumber) {
        super(id, firstName, lastName, addressLine1,/* addressLine2, */ postalCode, eMailAddress, phoneNumber);
    }
}
