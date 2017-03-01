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
    private int accountType;

    public Account() {
    }

    public Account(String accountNumber, int kundenummer, BigInteger kroner, int oere) {
        this.accountNumber = accountNumber;
        this.customerNumber = kundenummer;
        this.kroner = kroner;
        this.oere = oere;
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

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }
}
