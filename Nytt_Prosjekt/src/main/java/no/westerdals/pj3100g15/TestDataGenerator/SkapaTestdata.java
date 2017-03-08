package no.westerdals.pj3100g15.TestDataGenerator;

/**
 * Created by Sven Craehn on 07.03.2017.
 */
public class SkapaTestdata {
/*
    public static void main(String[] args) {
        //Testdata till att fylla Fasta_Overforinger i databasen.
       // String insertString = "INSERT INTO Faste_Overforingar (Avsenderkonto, Mottakerkonto, KundeID, Kroner, Oere, Melding_kid, Neste_Overforing, Intervall, Sluttdato) VALUES ";


        //Testdata till att fylla Logg_Transaksjoner i databasen.
        Random random = new Random();

        String insertString = "INSERT INTO Logg_Transaksjoner (Avsenderkonto, Mottakerkonto, Kroner, Oere, Transaksjonstype, Kjopested, AvsenderID, MottakerID) VALUES ";
        for (int i = 1; i < 201; i++) {
            int avs = random.nextInt(200) + 1;
            int mot = random.nextInt(200) + 1;
            while (avs == mot){
                mot = random.nextInt(200) + 1;
            }

            List<Account> accounts = DBService.getCustomerAccounts(avs);
            List<Account> accounts2 = DBService.getCustomerAccounts(mot);

            String avsenderkonto = accounts.get(0).getAccountNumber();
            String mottakerkonto = accounts2.get(0).getAccountNumber();
            int rdm = random.nextInt(501);
            int shrt = random.nextInt(2) + 1;
            insertString += "(" + avsenderkonto + ", " + mottakerkonto + ", " + rdm + ", " + 0 + ", " +
                    shrt + ", '" + "Rema 1000 Bjerke" + "', " + avs + ", " + mot + ")";
            if (i == 200)
                insertString += ";";
            else
                insertString += ", ";
        }

        System.out.println(insertString);
    } */
}
