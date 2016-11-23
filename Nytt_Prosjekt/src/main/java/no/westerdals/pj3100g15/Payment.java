package no.westerdals.pj3100g15;

public class Payment {
    private long payerId;
    private long receiverId;
    private long amount;

    public Payment() {
    }

    public Payment(final long payerId, final long receiverId, final long amount) {
        this.payerId = payerId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public long getPayerId() {
        return payerId;
    }

    public long getReceiverId() {
        return receiverId;
    }

    public long getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "payerId=" + payerId +
                ", receiverId=" + receiverId +
                ", amount=" + amount +
                '}';
    }
}
