package no.westerdals.PJ3100g15;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.sun.jmx.snmp.Timestamp;

import java.math.BigInteger;

/**
 * Created by Sven Craehn on 22.11.2016.
 */

@DatabaseTable (tableName = "Bankkonto")
public class Bankkonto {
    @DatabaseField //(id = true)
    private BigInteger kontonummer;
    @DatabaseField
    private int kundenummer, oere;
    @DatabaseField
    private BigInteger kroner;

    public Bankkonto(){

    }

    public Bankkonto(BigInteger kontonummer, int kundenummer, BigInteger kroner, int oere){
        this.kontonummer = kontonummer;
        this.kundenummer = kundenummer;
        this.kroner = kroner;
        this.oere = oere;
    }

    public BigInteger getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(BigInteger kontonummer) {
        this.kontonummer = kontonummer;
    }

    public int getKundenummer() {
        return kundenummer;
    }

    public void setKundenummer(int kundenummer) {
        this.kundenummer = kundenummer;
    }

    public int getOere() {
        return oere;
    }

    public void setOere(int oere) {
        this.oere = oere;
    }

    public BigInteger getKroner() {
        return kroner;
    }

    public void setKroner(BigInteger kroner) {
        this.kroner = kroner;
    }
}
