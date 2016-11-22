package no.westerdals.PJ3100g15.ORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable (tableName = "Customer")
public class Customer {

    @DatabaseField(columnName = "Kundenummer",id = true)
    private int customerID;
    @DatabaseField(columnName = "Foedselsnummer")
    private String birthdayNumber; //denne er bigInt i databasen
    @DatabaseField(columnName = "Fornavn")
    private String firstName;
    @DatabaseField (columnName = "Etternavn")
    private String surName;
    @DatabaseField (columnName = "Adresse")
    private String Address;
    @DatabaseField (columnName = "Postnummer")
    private int postalCode;
    @DatabaseField (columnName = "Mail")
    private String eMail;
    @DatabaseField (columnName = "Telefon")
    private String phoneNumber; //denne er int i databasen
    @DatabaseField (columnName = "Score")
    private int score;

    public Customer() {
        //Empty constructor
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getBirthdayNumber() {
        return birthdayNumber;
    }

    public void setBirthdayNumber(String birthdayNumber) {
        this.birthdayNumber = birthdayNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
