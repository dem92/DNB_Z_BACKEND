/*
 * Copyright (c) 2017. Group 15, PJ-3100, Westerdals Oslo ACT
 */

package no.westerdals.pj3100g15.ValidateData;

import java.math.BigInteger;

public class ValidateInput {

    /**
     * Checks if the variable kroner is negative
     * @param kroner BigInteger
     * @return boolean
     */
    public static boolean validateKroner(BigInteger kroner){
        if(kroner.compareTo(BigInteger.ZERO) >= 0){
            return true;
        }
        return false;
    }

    /**
     * Checks if oere is negative or more than 100
     * @param oere Integer
     * @return boolean
     */
    public static boolean validateOere(int oere){
        if (oere < 99 || oere >= 0) {
            return true;
        }
        return false;
    }

    /**
     * checks if input is negative or not, returns true if compareTo() returns -1
     * @param kroner BigInteger
     * @param oere Integer
     * @return boolean
     */
    public static boolean validateInput(BigInteger kroner, int oere){
        if(validateKroner(kroner) && validateOere(oere)){
            return true;
        }
        return false;
    }
}
