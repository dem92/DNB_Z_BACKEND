/*
 * Copyright (c) 2017. Group 15, PJ-3100, Westerdals Oslo ACT
 */

package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Bruker")
public class Customer {

    @DatabaseField(columnName = "Kundenummer", id = true)
    private Integer customerID = null;
    @DatabaseField(columnName = "Foedselsnummer")
    private String birthdayNumber;
    @DatabaseField(columnName = "Fornavn")
    private String firstName;
    @DatabaseField(columnName = "Etternavn")
    private String surName;
    @DatabaseField(columnName = "Adresse")
    private String address;
    @DatabaseField(columnName = "Postnummer")
    private int postalCode;
    @DatabaseField(columnName = "Mail")
    private String eMail;
    @DatabaseField(columnName = "Telefon")
    private int phoneNumber;
    @DatabaseField(columnName = "Score")
    private int score;

    public Customer() {
        //Empty constructor
    }

    public Customer(int customerID, String birthdayNumber, String firstName, String surName, String address, int postalCode, String eMail, int phoneNumber, int score) {
        this.customerID = customerID;
        this.birthdayNumber = birthdayNumber;
        this.firstName = firstName;
        this.surName = surName;
        this.address = address;
        this.postalCode = postalCode;
        this.eMail = eMail;
        this.phoneNumber = phoneNumber;
        this.score = score;
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
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
