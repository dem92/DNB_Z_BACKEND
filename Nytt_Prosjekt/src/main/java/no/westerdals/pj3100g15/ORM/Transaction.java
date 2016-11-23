//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package no.westerdals.PJ3100g15.ORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sun.jmx.snmp.Timestamp;
import java.math.BigInteger;

@DatabaseTable(
        tableName = "Transaksjon"
)
public class Transaction {
    @DatabaseField(id = true)
    private int id;
    @DatabaseField(columnName = "Tidspunkt")
    private Timestamp dateAndTime;
    @DatabaseField
    private BigInteger kroner;
    @DatabaseField(columnName = "Avsender_Kontonummer")
    private BigInteger senderAccountNumber;
    @DatabaseField(columnName = "Mottaker_Kontonummer")
    private BigInteger recipientAccountNumber;
    @DatabaseField
    private int oere;

    public Transaction() {
    }

    public Transaction(int id, Timestamp tidspunkt, BigInteger kroner, int oere, BigInteger avsender_kontonummer, BigInteger mottaker_kontonummer) {
        this.id = id;
        this.dateAndTime = tidspunkt;
        this.kroner = kroner;
        this.oere = oere;
        this.senderAccountNumber = avsender_kontonummer;
        this.recipientAccountNumber = mottaker_kontonummer;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return this.dateAndTime;
    }

    public void setTime(Timestamp tidspunkt) {
        this.dateAndTime = tidspunkt;
    }

    public BigInteger getKroner() {
        return this.kroner;
    }

    public void setKroner(BigInteger kroner) {
        this.kroner = kroner;
    }

    public int getOere() {
        return this.oere;
    }

    public void setOere(int oere) {
        this.oere = oere;
    }

    public BigInteger getAvsender() {
        return this.senderAccountNumber;
    }

    public void setAvsender(BigInteger avsender_kontonummer) {
        this.senderAccountNumber = avsender_kontonummer;
    }

    public BigInteger getMottaker() {
        return this.recipientAccountNumber;
    }

    public void setMottaker(BigInteger mottaker_kontonummer) {
        this.recipientAccountNumber = mottaker_kontonummer;
    }
}
