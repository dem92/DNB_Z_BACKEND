package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.io.FileReader;
import java.util.Properties;

public class DBConnector {

    /**
     *
     * @return
     */
    public static ConnectionSource makeConnection() {

        //Hardcoded usernames and passwords because of lack of time. This is the worst security EVER, but it works...
        String userName = "daheva15_MainUsr";
        String password = "PJ3100gruppe15!";
        String databaseUrl = "jdbc:mysql://tek.westerdals.no:3306/daheva15_PJ3100_gruppe15";

        return connectToDatabase(userName, password, databaseUrl);
    }

    /**
     *
     * @param user
     * @param password
     * @param databaseUrl
     * @return
     */
    public static ConnectionSource connectToDatabase(String user, String password, String databaseUrl) {
        try {
            JdbcPooledConnectionSource connectionSource =
                    new JdbcPooledConnectionSource(databaseUrl, user, password);
            connectionSource.setMaxConnectionAgeMillis(5 * 60 * 1000);
            return connectionSource;
        } catch (Exception e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }
}
