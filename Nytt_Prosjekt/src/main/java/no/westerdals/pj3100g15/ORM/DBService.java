package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Eva Dahlo on 23/11/2016.
 */
public class DBService {
    public static List<Customer> getCustomers(){
        ConnectionSource connectionSource = DBConnector.makeConnection();

        try {
            Dao<Customer, String> customerDao = DaoManager.createDao(connectionSource, Customer.class);

            List<Customer> customers = customerDao.queryForAll();
            return customers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
