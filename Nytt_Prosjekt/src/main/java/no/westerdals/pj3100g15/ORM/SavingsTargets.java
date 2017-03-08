package no.westerdals.pj3100g15.ORM;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigInteger;
import java.sql.Timestamp;

@DatabaseTable(tableName = "Sparemal")
public class SavingsTargets {
    @DatabaseField(columnName = "ID", id=true)
    private int id;
    @DatabaseField(columnName = "Kontonummer")
    private String accountNumber;
    @DatabaseField(columnName = "Tidspunkt")
    private Timestamp time;
    @DatabaseField(columnName = "Navn")
    private String name;
    @DatabaseField(columnName = "KundeID")
    private int customerId;
    @DatabaseField(columnName = "Sparte_kroner")
    private BigInteger savedKroner;
    @DatabaseField(columnName = "Sparte_oere")
    private int savedOere;
    @DatabaseField(columnName = "Mal_kroner")
    private BigInteger goalKroner;
    @DatabaseField(columnName = "Mal_oere")
    private int goalOere;
    @DatabaseField(columnName = "Ferdig")
    private boolean done;

    public SavingsTargets() {

    }

    public SavingsTargets(int id, String accountNumber, Timestamp time, String name, int customerId, BigInteger savedKroner, int savedOere, BigInteger goalKroner, int goalOere, boolean done) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.time = time;
        this.name = name;
        this.customerId = customerId;
        this.savedKroner = savedKroner;
        this.savedOere = savedOere;
        this.goalKroner = goalKroner;
        this.goalOere = goalOere;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public BigInteger getSavedKroner() {
        return savedKroner;
    }

    public void setSavedKroner(BigInteger savedKroner) {
        this.savedKroner = savedKroner;
    }

    public int getSavedOere() {
        return savedOere;
    }

    public void setSavedOere(int savedOere) {
        this.savedOere = savedOere;
    }

    public BigInteger getGoalKroner() {
        return goalKroner;
    }

    public void setGoalKroner(BigInteger goalKroner) {
        this.goalKroner = goalKroner;
    }

    public int getGoalOere() {
        return goalOere;
    }

    public void setGoalOere(int goalOere) {
        this.goalOere = goalOere;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
