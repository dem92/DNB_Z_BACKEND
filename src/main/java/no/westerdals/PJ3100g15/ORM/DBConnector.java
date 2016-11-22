package no.westerdals.PJ3100g15.ORM;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import java.io.FileReader;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnector {
    public DBConnector() {

    }


    public ConnectionSource makeConnection() {

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
            System.out.println(e);
        }

        //Kaller på privat metode
        return connectToDatabase(userName,password,databaseUrl);
        }

        private ConnectionSource connectToDatabase(String user, String password, String databaseUrl){
            //lager
            try {
            ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl, user, password);
            return connectionSource;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }}