package no.westerdals.PJ3100g15;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * Class for creating a connection to a database.
 *
 * Created by Eva Dahlo on 07/11/2016.
 */
public class DBConnector {

    /**
     * Method that creates a connection to a database,
     * using information from a .properties file.
     *
     * @return A connection to a database
     */
    public static ConnectionSource makeConnection(){
        try {
            ConnectionSource connectionSource = new JdbcConnectionSource("jdbc:mysql://tek.westerdals.no:3306/daheva15_PJ3100_gruppe15", "daheva15_MainUsr", "PJ3100gruppe15!");

            return connectionSource;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Bankkonto> getAccounts(){
        try {
            Dao<Bankkonto, String> bankkontoDao = DaoManager.createDao(makeConnection(), Bankkonto.class);

            List<Bankkonto> accounts = bankkontoDao.queryForAll();
            return accounts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
