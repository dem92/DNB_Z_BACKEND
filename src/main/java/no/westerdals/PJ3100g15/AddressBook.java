package no.westerdals.PJ3100g15;

import java.util.ArrayList;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public class AddressBook {
    private ArrayList<String> addresses;

    public AddressBook(){
        addresses = new ArrayList<String>();
    }

    public boolean addAddress(String id){
        if (id == null) return false;
        addresses.add(id);
        return true;
    }

    public boolean removeAddress(String id){ // TODO add code for removal.
        if (id == null) return false;

        return true;
    }
}
