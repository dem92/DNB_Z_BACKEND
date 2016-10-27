package no.westerdals.PJ3100g15;

import org.sql2o.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by labbetuss on 10/27/16.
 */
public class Database {
    // We create a DB connection for further use. TODO - Move the username/password out of this file
    Sql2o sql2o;
    // Name of columns in the database doesn't match fields in customer class
    public Map<String, String> colMaps = new HashMap<>();
    public List<Customer> testList;

    public Database() {
        sql2o = new Sql2o("mysql://tek.westerdals.no:3306/davdan15_pj15", "davdan15_root", "Evaerbest!");

        colMaps.put("FOEDSELNUMMER", "id");
        colMaps.put("FORNAVN", "firstName");
        colMaps.put("ETTERNAVN", "lastName");
        colMaps.put("ADRESSE", "addressLine1");
        colMaps.put("POSTNUMMER", "postalCode");
        colMaps.put("MAIL", "eMailAddress");
        colMaps.put("TELEFON", "phoneNumber");
        colMaps.put("SCORE", "score");

        sql2o.setDefaultColumnMappings(colMaps);
    }

    //TODO COMMENT THE SHIT OUT OF THIS
    public static void main(String[] args)
    {
        Database testDB = new Database();
        testDB.testMethod();

    }

    private void testMethod() {
        testList = getAllCustomers();
        for (Customer cust: testList)
        {
            System.out.println(cust.toString());
        }
    }


    public List<Customer> getAllCustomers()
    {
        String sql = "SELECT Foedselsnummer, Fornavn, Etternavn, Adresse, Postnummer, Mail, Telefon, Score\n" +
                "FROM Bruker";
        try(Connection con = sql2o.open())
        {
            return con.createQuery(sql).executeAndFetch(Customer.class);
        }
    }


}
