import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Sven Craehn on 25.10.2016.
 */
public class main {
    public static ArrayList<String> maleName = new ArrayList<>();
    public static ArrayList<String> femaleName = new ArrayList<>();
    public static ArrayList<String> lastName = new ArrayList<>();
    public static Random random = new Random();


    public static void main(String[] args){
        maleName.add("Anders");
        maleName.add("Sven");
        maleName.add("Nils");
        maleName.add("Arne");
        maleName.add("Björn");
        maleName.add("Olle");
        maleName.add("Kent");
        maleName.add("Erik");
        maleName.add("Mattias");
        maleName.add("Jens");
        femaleName.add("Eva");
        femaleName.add("Malin");
        femaleName.add("Citona");
        femaleName.add("Pia");
        femaleName.add("Gunilla");
        femaleName.add("Berit");
        femaleName.add("Aurora");
        femaleName.add("Susanne");
        femaleName.add("Annika");
        femaleName.add("Bibbi");
        lastName.add("Johansson");
        lastName.add("Nilsson");
        lastName.add("Gunnarsson");
        lastName.add("Olsson");
        lastName.add("Von Pluring");
        lastName.add("Anka");

        for(int i = 0; i < 100; i++){
            System.out.print("Name: ");
            createMaleName();
            System.out.print("Account number: ");
            createBankAccount();
            System.out.print("Social security number: ");
            createSSN();
            System.out.println("");
            System.out.print("Name: ");
            createFemaleName();
            System.out.print("Account number: ");
            createBankAccount();
            System.out.print("Social security number: ");
            createSSN();
            System.out.println("");
        }
    }

    private static void createMaleName(){
        String fullName = new String("");
        fullName += maleName.get(random.nextInt(maleName.size())) + " ";
        fullName += lastName.get(random.nextInt(lastName.size()));
        System.out.println(fullName);
    }

    private static void createFemaleName(){
        String fullName = new String("");
        fullName += femaleName.get(random.nextInt(femaleName.size())) + " ";
        fullName += lastName.get(random.nextInt(lastName.size()));
        System.out.println(fullName);
    }

    private static void createBankAccount(){
        int first = ThreadLocalRandom.current().nextInt(1000, 9999);
        int middle = ThreadLocalRandom.current().nextInt(10, 99);
        int last = ThreadLocalRandom.current().nextInt(10000, 99999);
        System.out.println(first + "." + middle + "." + last);
    }

    private static void createSSN(){
        GregorianCalendar gc = new GregorianCalendar();

        int year = ThreadLocalRandom.current().nextInt(1995, 2003);
        gc.set(gc.YEAR, year);
        String stringYear = Integer.toString(year);

        int month = ThreadLocalRandom.current().nextInt(1, 12);
        gc.set(gc.MONTH, month);

        String stringMonth = "";
        if (month < 10) {
            stringMonth += "0" + Integer.toString(month);
        } else stringMonth += Integer.toString(month);

        int day = ThreadLocalRandom.current().nextInt(1, gc.getActualMaximum(gc.DAY_OF_MONTH));
        gc.set(gc.DAY_OF_MONTH, day);

        String stringDay = "";
        if (day < 10) {
            stringDay += "0" + Integer.toString(day);
        } else stringDay += Integer.toString(day);

        int lastFive = ThreadLocalRandom.current().nextInt(10000, 99999);

        System.out.println(stringDay + stringMonth + stringYear.substring(2) + "-" + lastFive);
    }
}
