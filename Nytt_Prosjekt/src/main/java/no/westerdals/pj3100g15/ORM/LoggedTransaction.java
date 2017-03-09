package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.math.BigInteger;

@DatabaseTable (tableName = "Logg_Transaksjoner")
public class LoggedTransaction {
    @DatabaseField(columnName = "", id = true)
    private int id;
    @DatabaseField(columnName = "Avsenderkonto")
    private String sendingAccount;
    @DatabaseField(columnName = "Mottakerkonto")
    private String recievingAccount;
    @DatabaseField(columnName = "Tidspunkt")
    private long timestamp;
    @DatabaseField(columnName = "Kroner")
    private BigInteger kroner;
    @DatabaseField(columnName = "Oere")
    private int oere;
    @DatabaseField(columnName = "Melding_kid")
    private String message_kid;
    @DatabaseField(columnName = "Transaksjonstype")
    private String transactionType;
    @DatabaseField(columnName = "AvsenderID")
    private int senderID;
    @DatabaseField(columnName = "MottakerID")
    private int receiverID;

    public LoggedTransaction() {
    }

    public LoggedTransaction(int id, String sendingAccount, String recievingAccount, long timestamp, BigInteger kroner, int oere, String message_kid, String transactionType, int senderID, int receiverID) {
        this.id = id;
        this.sendingAccount = sendingAccount;
        this.recievingAccount = recievingAccount;
        this.timestamp = timestamp;
        this.kroner = kroner;
        this.oere = oere;
        this.message_kid = message_kid;
        this.transactionType = transactionType;
        this.senderID = senderID;
        this.receiverID = receiverID;
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

    public String getRecievingAccount() {
        return recievingAccount;
    }

    public void setRecievingAccount(String recievingAccount) {
        this.recievingAccount = recievingAccount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

    public String getMessage_kid() {
        return message_kid;
    }

    public void setMessage_kid(String message_kid) {
        this.message_kid = message_kid;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getSenderID() {
        return senderID;
    }

    public void setSenderID(int senderID) {
        this.senderID = senderID;
    }

    public int getReceiverID() {
        return receiverID;
    }

    public void setReceiverID(int receiverID) {
        this.receiverID = receiverID;
    }
}
