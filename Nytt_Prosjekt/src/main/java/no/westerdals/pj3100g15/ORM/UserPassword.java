package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by Eva Dahlo on 22/11/2016.
 */
@DatabaseTable (tableName = "Passord_Bruker")
public class UserPassword {

    @DatabaseField (columnName = "Kundenummer", id = true)
    private int customerID;
    @DatabaseField (columnName = "Hash_av_passord")
    private String passwordHash;

    public UserPassword() {
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
