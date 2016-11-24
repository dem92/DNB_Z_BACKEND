package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.FileReader;
import java.util.Properties;

public class DBConnector {

    public static ConnectionSource makeConnection() {

        String userName = "";
        String password = "";
        String databaseUrl = "";

        //Unngår hardkoding i programmet
        //Leser brukernavn, databaseurl og passord fra properties-fil(db.properties).
        try {
            FileReader fileReader = new FileReader("db.properties");
            Properties properties = new Properties();
            properties.load(fileReader);
            userName = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
            databaseUrl = properties.getProperty("db.databaseUrl");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Kaller på privat metode
        return connectToDatabase(userName, password, databaseUrl);
    }

    public static ConnectionSource connectToDatabase(String user, String password, String databaseUrl) {
        try {
            ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl, user, password);
            return connectionSource;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
