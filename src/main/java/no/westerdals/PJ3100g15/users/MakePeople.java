package no.westerdals.PJ3100g15.users;


import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Sven Craehn on 26.10.2016.
 */
public class MakePeople {
    public static ArrayList<String> firstNameMale = new ArrayList<>();
    public static ArrayList<String> firstNameFemale = new ArrayList<>();
    public static ArrayList<String> lastName = new ArrayList<>();

    public static ArrayList<String> nameMale = new ArrayList<>();
    public static ArrayList<String> nameFemale = new ArrayList<>();
    public static ArrayList<String> surname = new ArrayList<>();
    public static ArrayList<String> address = new ArrayList<>();
    public static ArrayList<Integer> zipCode = new ArrayList<>();
    public static ArrayList<String> email = new ArrayList<>();
    public static ArrayList<Integer> phoneNo = new ArrayList<>();
    public static ArrayList<String> socialSecurityNo = new ArrayList<>();
    public static ArrayList<String> bankAccount = new ArrayList<>();
    public static ArrayList<Integer> accountBalance = new ArrayList<>();
    public static Random random = new Random();

    public void main(String[] args){
        createData();
    }

    public void createData(){

        fillNameLists();

        for(int i = 0; i < 100; i++){
            createMaleNames();
            createFemaleNames();
            for(int j = 0; j < 2; j++) {
                createPhone();
                createLastNames();
                createBankAccount();
                createAddress();
                createZipCode();
                createSSN();
                createBalance();
            }
        }
        createMail();

/*
        System.out.println("Personnummer: " + socialSecurityNo);
        System.out.println(socialSecurityNo.size());
        System.out.println("Navn: " + nameMale);
        System.out.println(nameMale.size());
        System.out.println("Navn: " + nameFemale);
        System.out.println(nameFemale.size());
        System.out.println("Etternavn: " + surname);
        System.out.println(surname.size());
        System.out.println("Adress: " + address);
        System.out.println(address.size());
        System.out.println("Postnr: " + zipCode);
        System.out.println(zipCode.size());
        System.out.println("Epost: " + email);
        System.out.println(email.size());
        System.out.println("Telefon: " + phoneNo);
        System.out.println(phoneNo.size());
        System.out.println("Kontonr: " + bankAccount);
        System.out.println(bankAccount.size());
        System.out.println("Saldo: " + accountBalance);
        System.out.println(accountBalance.size());
        */

        for(int i = 180; i < 200; i++){
            System.out.println("UPDATE Bruker SET Foedselsnr = '" + socialSecurityNo.get(i) + "' WHERE Kundenummer = " + i + ";");
        }
    }

    private static void fillNameLists(){
        firstNameMale.add("Anders");
        firstNameMale.add("Sven");
        firstNameMale.add("Nils");
        firstNameMale.add("Arne");
        firstNameMale.add("Björn");
        firstNameMale.add("Olle");
        firstNameMale.add("Kent");
        firstNameMale.add("Erik");
        firstNameMale.add("Mattias");
        firstNameMale.add("Jens");
        firstNameFemale.add("Eva");
        firstNameFemale.add("Malin");
        firstNameFemale.add("Citona");
        firstNameFemale.add("Pia");
        firstNameFemale.add("Gunilla");
        firstNameFemale.add("Berit");
        firstNameFemale.add("Aurora");
        firstNameFemale.add("Susanne");
        firstNameFemale.add("Annika");
        firstNameFemale.add("Bibbi");
        lastName.add("Johansson");
        lastName.add("Nilsson");
        lastName.add("Gunnarsson");
        lastName.add("Olsson");
        lastName.add("Von Pluring");
        lastName.add("Anka");
    }

    private static void createMaleNames(){
        String name = new String("");
        name += firstNameMale.get(random.nextInt(firstNameMale.size()));
        nameMale.add(name);
    }

    private static void createFemaleNames(){
        String name = new String("");
        name += firstNameFemale.get(random.nextInt(firstNameFemale.size()));
        nameFemale.add(name);
    }

    private static void createLastNames(){
        String name = new String("");
        name += lastName.get(random.nextInt(lastName.size()));
        surname.add(name);
    }

    private static void createAddress(){
        String street = new String("Norgegata ");
        String streetNr = String.valueOf(ThreadLocalRandom.current().nextInt(1, 356));
        address.add(street + streetNr);
    }

    private static void createZipCode(){
        int zip = ThreadLocalRandom.current().nextInt(1000, 9999);
        zipCode.add(zip);
    }

    private static void createMail(){
        for(int i = 0; i < 100; i++){
            String mail = nameMale.get(i) + surname.get(i) + "@gmail.com";
            mail = mail.replaceAll(" ", "_").toLowerCase();
            email.add(mail);
            String mail2 = nameFemale.get(i) + surname.get(i+100) + "@gmail.com";
            mail2 = mail2.replaceAll(" ", "_").toLowerCase();
            email.add(mail2);
        }
    }

    private static void createBankAccount(){
        int first = ThreadLocalRandom.current().nextInt(100000, 999999);
        String frst = Integer.toString(first);
        int last = ThreadLocalRandom.current().nextInt(10000, 99999);
        String lst = Integer.toString(last);
        bankAccount.add(frst + lst);
    }

    private static void createPhone(){
        int phone = ThreadLocalRandom.current().nextInt(10000000, 99999999);
        phoneNo.add(phone);
    }

    private static void createSSN(){
        int yr = ThreadLocalRandom.current().nextInt(1995, 2003);
        String stringYear = Integer.toString(yr).substring(2);

        int mth = ThreadLocalRandom.current().nextInt(1, 12);
        String stringMonth = "";
        if (mth < 10) {
            stringMonth += "0" + Integer.toString(mth);
        } else stringMonth += Integer.toString(mth);

        int day = ThreadLocalRandom.current().nextInt(1,30);
        String stringDay = "";
        if (day < 10){
            stringDay += "0" + Integer.toString(day);
        } else stringDay += Integer.toString(day);

        socialSecurityNo.add(stringDay + stringMonth + stringYear + Integer.toString(ThreadLocalRandom.current().nextInt(10000, 99999)));
    }

    private static void createBalance(){
        int balance = ThreadLocalRandom.current().nextInt(1,999999);
        accountBalance.add(balance);
    }
}
