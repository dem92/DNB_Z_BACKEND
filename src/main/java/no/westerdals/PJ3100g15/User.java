package no.westerdals.PJ3100g15;

/**
 * Created by Eva Dahlo on 29/09/2016.
 */
public abstract class User {
    protected final int foedselsnummer;
    private String firstName;
    private String lastName;
    private String addressLine1;
    /*
    private String addressLine2; TODO - Check if we really want to have this second address line
    */
    private int postalCode;
    private String eMailAddress;
    private int phoneNumber;

    public User() {
        foedselsnummer = 0;
    }

    public User(int id, String firstName, String lastName, String addressLine1, /* String addressLine2, */ int postalCode, String eMailAddress, int phoneNumber) {
        this.foedselsnummer = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.addressLine1 = addressLine1;
    //    this.addressLine2 = addressLine2;
        this.postalCode = postalCode;
        this.eMailAddress = eMailAddress;
        this.phoneNumber = phoneNumber;
    }

    // Get and set.
    public int getFoedselsnummer() {
        return foedselsnummer;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /*
    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }
    */

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String geteMailAddress() {
        return eMailAddress;
    }

    public void seteMailAddress(String eMailAddress) {
        this.eMailAddress = eMailAddress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String toString(){
        return foedselsnummer + " " +
                firstName + " " +
                lastName + " " +
                addressLine1 + " " +
            /*    addressLine2 + " " + */
                postalCode + " " +
                eMailAddress + " " +
                phoneNumber;
    }
}
