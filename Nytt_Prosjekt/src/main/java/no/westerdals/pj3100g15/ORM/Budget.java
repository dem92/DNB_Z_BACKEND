package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "Budsjett")
public class Budget {
    @DatabaseField(columnName = "ID", id = true)
    private int id;
    @DatabaseField(columnName = "KundeID")
    private int customerId;

    public Budget() {
        //Left empty on purpose
    }

    public Budget(int id, int customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
