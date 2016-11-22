package no.westerdals.PJ3100g15.ORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Bruker {

    @DatabaseField(columnName = "Kundenummer",id = true)
    private int userNumber;
    @DatabaseField(columnName = "Foedselsnummer")
    private String birthdayNumber; //denne er bigInt i databasen
    @DatabaseField()
    private String Fornavn;
    @DatabaseField
    private String Etternavn;
    @DatabaseField
    private String Adresse;
    @DatabaseField
    private int Postnummer;
    @DatabaseField
    private String Mail;
    @DatabaseField
    private String Telefon; //denne er int i databasen
    @DatabaseField
    private int Score;

    public Bruker() {
        //Empty constructor
    }

}
