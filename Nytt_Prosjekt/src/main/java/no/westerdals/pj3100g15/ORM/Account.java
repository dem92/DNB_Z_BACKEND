//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigInteger;

@DatabaseTable(tableName = "Bankkonto")
public class Account {
    @DatabaseField(columnName = "Kontonummer", id = true)
    private String accountNumber;
    @DatabaseField(columnName = "Kundenummer")
    private int customerNumber;
    @DatabaseField
    private int oere;
    @DatabaseField
    private BigInteger kroner;
    @DatabaseField(columnName = "Kontotype")
    private String accountType;
    @DatabaseField(columnName = "Rente")
    private double interest;
    @DatabaseField(columnName = "Main")
    private int main;
    @DatabaseField(columnName = "Kontonavn")
    private String name;

    public Account() {
        //Left empty on purpose
    }

    public Account(String accountNumber, int kundenummer, BigInteger kroner, int oere, double interest) {
        this.accountNumber = accountNumber;
        this.customerNumber = kundenummer;
        this.kroner = kroner;
        this.oere = oere;
        this.interest = interest;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getCustomerNumber() {
        return this.customerNumber;
    }

    public void setCustomerNumber(int customerNumber) {
        this.customerNumber = customerNumber;
    }

    public int getOere() {
        return this.oere;
    }

    public void setOere(int oere) {
        this.oere = oere;
    }

    public BigInteger getKroner() {
        return this.kroner;
    }

    public void setKroner(BigInteger kroner) {
        this.kroner = kroner;
    }

    public String getAccountType() {
        return accountType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public int getMain() {
        return main;
    }

    public void setMain(int main) {
        this.main = main;
    }
}
