package no.westerdals.pj3100g15;

public class Transactions {
    private int kroner;
    private int oere;
    private boolean coverage;
    private BankAccount sending;
    private BankAccount receiving;

    public Transactions(int kroner, int oere, BankAccount sending, BankAccount receiving) {

        //Initialiserer og deklarerer variabler
        this.kroner = kroner;
        this.oere = oere;
        this.sending = sending;
        this.receiving = receiving;
        coverage = false;

        //Metoder
        setCoverage(controlAccounts());
        transferPayment();
    }

    public boolean controlAccounts() {
        System.out.println("Kontrollerer kontoer.");
        int tempAmount = sending.getKroner();
        setCoverage(false);
        if (tempAmount >= getKroner()) {
            setCoverage(true);
        } else if (tempAmount == getKroner()) {
            tempAmount = sending.getOere();
            if (tempAmount >= getOere()) {
                setCoverage(true);
            }
        }
        return isCoverage();
    }


    public void transferPayment() {
        //både kroner og øre-feltet må oppdateres for begge kontoene.
        // Benytter subtractAmount og addAmount-metodene i Account-klassen
        if (isCoverage()) {
            sending.subtractAmount(getKroner(),getOere());
            sending.logTransfer(sending.getAccountNumber(), receiving.getAccountNumber(), kroner, oere);
            receiving.addAmount(getKroner(), getOere());
            receiving.logTransfer(sending.getAccountNumber(), receiving.getAccountNumber(), kroner, oere);
            System.out.println("Overføringen ble gjennomført.");
        } else {
            System.out.println("Overføringen ble ikke godkjent.");
        }
        System.out.println("Saldo på avsender: "+sending.getKroner()+", "+sending.getOere());
        System.out.println("Saldo på mottaker: "+receiving.getKroner()+", "+receiving.getOere());
    }

    //Gettere og settere
    public int getKroner() {
        return kroner;
    }

    public void setKroner(int kroner) {
        this.kroner = kroner;
    }

    public int getOere() {
        return oere;
    }

    public void setOere(int oere) {
        this.oere = oere;
    }

    public boolean isCoverage() {
        return coverage;
    }

    public void setCoverage(boolean coverage) {
        this.coverage = coverage;
    }

    public BankAccount getSending() {
        return sending;
    }

    public BankAccount getReceiving() {
        return receiving;
    }

    public void setReceiving(BankAccount receiving) {
        this.receiving = receiving;
    }

    public void setSending(BankAccount sending) {
        this.sending = sending;
    }
}