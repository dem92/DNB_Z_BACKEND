/*
 * Copyright (c) 2017. Group 15, PJ-3100, Westerdals Oslo ACT
 */

package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Passord_Bruker")
public class UserPassword {

    @DatabaseField (columnName = "Foedselsnummer", id = true)
    private String customerID;
    @DatabaseField (columnName = "Hash_av_passord")
    private String passwordHash;
    @DatabaseField (columnName = "Klartekst_passord")
    private String passwordPlain;

    public UserPassword() {
        //Left empty on purpose
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordPlain() {
        return passwordPlain;
    }

    public void setPasswordPlain(String passwordPlain) {
        this.passwordPlain = passwordPlain;
    }
}
