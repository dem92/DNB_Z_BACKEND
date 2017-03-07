package no.westerdals.pj3100g15.TestDataGenerator;

/**
 * Created by Sven Craehn on 07.03.2017.
 */
public class SkapaTestdata {
/*
    public static void main(String[] args) {
        Random random = new Random();

        String insertString = "INSERT INTO Logg_Transaksjoner (Avsenderkonto, Mottakerkonto, Kroner, Oere, Melding_kid, Transaksjonstype, AvsenderID, MottakerID) VALUES ";
        for (int i = 1; i < 201; i++) {
            List<Account> accounts = DBService.getCustomerAccounts(DBService.getCustomer(i).getCustomerID());
            List<Account> accounts2 = DBService.getCustomerAccounts(DBService.getCustomer(i+1).getCustomerID());

            String avsenderkonto = accounts.get(0).getAccountNumber();
            String mottakerkonto = accounts2.get(0).getAccountNumber();
            int rdm = random.nextInt(12500);
            insertString += "(" + avsenderkonto + ", " + mottakerkonto + ", " + rdm + ", " + 0 + ", '" + "Husleie mars" + "', '" +
                    "payment" + "', " + DBService.getCustomer(i).getCustomerID() + ", " + DBService.getCustomer(i+1).getCustomerID() + ")";
            if (i == 200)
                insertString += ";";
            else
                insertString += ", ";
        }

        System.out.println(insertString);
    }
    */
}
