package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "BudsjettKategori")
public class BudgetCategory {
    @DatabaseField(columnName = "ID", id = true)
    private int id;
    @DatabaseField(columnName = "BudsjettID")
    private int budgetId;
    @DatabaseField(columnName = "Kategori")
    private String category;
    @DatabaseField(columnName = "Planlagt_Belop")
    private int goalAmount;
    @DatabaseField(columnName = "Brukt_Belop")
    private int usedAmount;

    public BudgetCategory() {
    }

    public BudgetCategory(int id, int budgetId, String category, int goalAmount, int usedAmount) {
        this.id = id;
        this.budgetId = budgetId;
        this.category = category;
        this.goalAmount = goalAmount;
        this.usedAmount = usedAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(int goalAmount) {
        this.goalAmount = goalAmount;
    }

    public int getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(int usedAmount) {
        this.usedAmount = usedAmount;
    }
}
