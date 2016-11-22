package no.westerdals.PJ3100g15;

import java.util.List;
import java.util.Random;

/**
 * Created by Sven Craehn on 22.11.2016.
 */
public class ScriptGenerator {

    private static Random random = new Random();

    public static void main(String[] args){
        List<Bankkonto> kontoer = DBConnector.getAccounts();
        System.out.println("Insert into Transaksjon values ");

        for (Bankkonto konto : kontoer){
            String ladida = "(null, null, " + getRandomNumber() + ", 0, " + konto.getKontonummer() + ", " + kontoer.get(getRandomIndex(kontoer.size())).getKontonummer() + "),";
            System.out.println(ladida);
        }
        for (Bankkonto konto : kontoer){
            String ladida = "(null, null, " + getRandomNumber() + ", 0, " + konto.getKontonummer() + ", " + kontoer.get(getRandomIndex(kontoer.size())).getKontonummer() + "),";
            System.out.println(ladida);
        }
        for (Bankkonto konto : kontoer){
            String ladida = "(null, null, " + getRandomNumber() + ", 0, " + konto.getKontonummer() + ", " + kontoer.get(getRandomIndex(kontoer.size())).getKontonummer() + "),";
            System.out.println(ladida);
        }
    }

    private static int getRandomNumber(){
        return random.nextInt(2000) + 78;
    }

    private static int getRandomIndex(int listSize){
        return random.nextInt(listSize);
    }
}
