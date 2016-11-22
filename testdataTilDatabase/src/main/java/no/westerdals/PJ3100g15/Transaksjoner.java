package no.westerdals.PJ3100g15;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sun.jmx.snmp.Timestamp;

import java.math.BigInteger;

/**
 * Created by Sven Craehn on 22.11.2016.
 */

@DatabaseTable (tableName = "Transaksjon")
public class Transaksjoner {
    @DatabaseField (id = true)
    private int id;
    @DatabaseField
    private Timestamp tidspunkt;
    @DatabaseField
    private BigInteger kroner, avsender_kontonummer, mottaker_kontonummer;
    @DatabaseField
    private int oere;

    public Transaksjoner(){

    }

    public Transaksjoner(int id, Timestamp tidspunkt, BigInteger kroner, int oere,
                         BigInteger avsender_kontonummer, BigInteger mottaker_kontonummer){
        this.id = id;
        this.tidspunkt = tidspunkt;
        this.kroner = kroner;
        this.oere = oere;
        this.avsender_kontonummer = avsender_kontonummer;
        this.mottaker_kontonummer = mottaker_kontonummer;
    }

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public Timestamp getTime() { return tidspunkt; }

    public void setTime(Timestamp tidspunkt) { this.tidspunkt = tidspunkt; }

    public BigInteger getKroner() { return kroner; }

    public void setKroner(BigInteger kroner) { this.kroner = kroner; }

    public int getOere() { return oere; }

    public void setOere(int oere) { this.oere = oere; }

    public BigInteger getAvsender() { return avsender_kontonummer; }

    public void setAvsender(BigInteger avsender_kontonummer) { this.avsender_kontonummer = avsender_kontonummer; }

    public BigInteger getMottaker() { return mottaker_kontonummer; }

    public void setMottaker(BigInteger mottaker_kontonummer) { this.mottaker_kontonummer = mottaker_kontonummer; }
}
