package no.westerdals.PJ3100g15;

import no.westerdals.PJ3100g15.users.Customer;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class Database {
    // We create a DB connection for further use. TODO - Move the username/password out of this file
    Sql2o sql2o;

    public Map<String, String> colMaps = new HashMap<>();
    public List<Customer> testList;

    public Database() {
        sql2o = new Sql2o("mysql://tek.westerdals.no:3306/davdan15_pj15", "davdan15_root", "Evaerbest!");

        // Name of columns in the database doesn't match fields in customer class
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


    public Integer returnInteger(String sql) {
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeScalar(Integer.class);
        } catch (Exception e){
            return null;
        }

    }

    public String returnString(String sql) {
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeScalar(String.class);
        } catch (Exception e){
            return null;
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
