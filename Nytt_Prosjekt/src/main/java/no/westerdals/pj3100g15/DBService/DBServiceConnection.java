package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.support.ConnectionSource;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.io.IOException;

public class DBServiceConnection {

    public static ConnectionSource connectionSource;

    /**
     * Should be called first thing in every method in this class that requires a working connection to the database.
     */
    public static void makeConnection() {
        if (connectionSource == null)
            connectionSource = DBConnector.makeConnection();
    }

    /**
     * This method is not needed because the connections to the database is timed out.
     */
    public static void closeConnection() {
        try {
            connectionSource.close();
        } catch (IOException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
    }
}
