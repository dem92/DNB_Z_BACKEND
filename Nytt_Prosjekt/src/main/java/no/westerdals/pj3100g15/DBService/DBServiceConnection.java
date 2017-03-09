package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.support.ConnectionSource;
import no.westerdals.pj3100g15.ORM.DBConnector;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.io.IOException;

/**
 * Created by Henrik on 09.03.2017.
 */
public class DBServiceConnection {
    public static ConnectionSource connectionSource;

    //Should be called first thing in every method in this class that requires a working connection to the database.
    public static void makeConnection() {
        if (connectionSource == null)
            connectionSource = DBConnector.makeConnection();
    }

    public static void closeConnection(){
        try {
            connectionSource.close();
        } catch (IOException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
    }
}