package no.westerdals.pj3100g15.ORM;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigInteger;
import java.sql.Timestamp;

@DatabaseTable(tableName = "Faste_Overforingar")
public class RecurringTransfer {
    @DatabaseField(columnName = "ID", id=true)
    private int id;
    @DatabaseField(columnName = "Avsenderkonto")
    private String sendingAccount;
    @DatabaseField(columnName = "Mottakerkonto")
    private String receivingAccount;
    @DatabaseField(columnName = "KundeId")
    private int customerId;
    @DatabaseField(columnName = "Kroner")
    private BigInteger kroner;
    @DatabaseField(columnName = "Oere")
    private int oere;
    @DatabaseField(columnName = "Melding_kid")
    private String message;
    @DatabaseField(columnName = "Neste_Overforing")
    private long nextTransfer;
    @DatabaseField(columnName = "Intervall")
    private String intervall;
    @DatabaseField(columnName = "Sluttdato")
    private long endDate;
    @DatabaseField(columnName = "Aktiv")
    private boolean active;

    public RecurringTransfer() {
    }

    public RecurringTransfer(int id, String sendingAccount, String receivingAccount, int customerId, BigInteger kroner, int oere, String message, long nextTransfer, String intervall, long endDate, boolean active) {
        this.id = id;
        this.sendingAccount = sendingAccount;
        this.receivingAccount = receivingAccount;
        this.customerId = customerId;
        this.kroner = kroner;
        this.oere = oere;
        this.message = message;
        this.nextTransfer = nextTransfer;
        this.intervall = intervall;
        this.endDate = endDate;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSendingAccount() {
        return sendingAccount;
    }

    public void setSendingAccount(String sendingAccount) {
        this.sendingAccount = sendingAccount;
    }

    public String getReceivingAccount() {
        return receivingAccount;
    }

    public void setReceivingAccount(String receivingAccount) {
        this.receivingAccount = receivingAccount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public BigInteger getKroner() {
        return kroner;
    }

    public void setKroner(BigInteger kroner) {
        this.kroner = kroner;
    }

    public int getOere() {
        return oere;
    }

    public void setOere(int oere) {
        this.oere = oere;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getNextTransfer() {
        return nextTransfer;
    }

    public void setNextTransfer(long nextTransfer) {
        this.nextTransfer = nextTransfer;
    }

    public String getIntervall() {
        return intervall;
    }

    public void setIntervall(String intervall) {
        this.intervall = intervall;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
