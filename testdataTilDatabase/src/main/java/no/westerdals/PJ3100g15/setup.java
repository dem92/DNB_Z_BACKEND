package no.westerdals.PJ3100g15;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Created by Sven Craehn on 26.10.2016.
 */
public class setup {

    public static void main(String[] args){
        testPeople test = new testPeople();
        test.createData();
        DBConnector dbConnector = new DBConnector();

        String insertString = "INSERT INTO Bankkonto VALUES ";
        for (int i = 0; i < 200; i++) {
            insertString += "(" + new BigInteger("" + test.bankAccount.get(i)) + ", "
                    + i + ", '";
                    if (i % 2 == 0)
                        insertString += "Brukskonto', ";
                    else
                        insertString += "Sparekonto', ";
                    insertString += new BigInteger("" + test.accountBalance.get(i)) + ", 0, "
                    + "2.5)";
            if (i == 199)
                insertString += ";";
            else
                insertString += ", ";
        }
        try (Connection connection = dbConnector.makeConnection(); java.sql.Statement statement = connection.createStatement()) {

            statement.executeUpdate(insertString);
        }
        catch (Exception e){
            System.out.println("ERROR!!!!");
        }
    }
}
