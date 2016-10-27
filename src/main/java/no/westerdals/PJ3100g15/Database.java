package no.westerdals.PJ3100g15;

import org.sql2o.Sql2o;

import java.util.List;

/**
 * Created by labbetuss on 10/27/16.
 */
public class Database {
    // We create a DB connection for further use. TODO - Move the username/password out of this file
    Sql2o con;


    public Database() {
        con = new Sql2o("jbdc:mysql://tek.westerdals.no:3306/davdan15_pj15", "davdan15_root", "Evaerbest!");
    }


    public List<Customer> getAllCustomers()
    {
        return null;
    }
}
