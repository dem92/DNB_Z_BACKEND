package no.westerdals.PJ3100g15.ORM;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Bruker {

    @DatabaseField
    private int Kundenummer;
    @DatabaseField
    private String Foedselsnummer; //denne er bigInt i databasen
    @DatabaseField
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

    public int getKundenummer() {
        return Kundenummer;
    }

    public void setKundenummer(int kundenummer) {
        Kundenummer = kundenummer;
    }

    public String getFoedselsnummer() {
        return Foedselsnummer;
    }

    public void setFoedselsnummer(String foedselsnummer) {
        Foedselsnummer = foedselsnummer;
    }

    public String getFornavn() {
        return Fornavn;
    }

    public void setFornavn(String fornavn) {
        Fornavn = fornavn;
    }

    public String getEtternavn() {
        return Etternavn;
    }

    public void setEtternavn(String etternavn) {
        Etternavn = etternavn;
    }

    public String getAdresse() {
        return Adresse;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public int getPostnummer() {
        return Postnummer;
    }

    public void setPostnummer(int postnummer) {
        Postnummer = postnummer;
    }

    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    public String getTelefon() {
        return Telefon;
    }

    public void setTelefon(String telefon) {
        Telefon = telefon;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }
}
