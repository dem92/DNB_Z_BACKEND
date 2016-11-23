package no.westerdals.PJ3100g15;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Eva Dahlo on 26/10/2016.
 */
public class DBConnector {
    private String user;
    private String password;
    private String database;
    private String server;

    public DBConnector(){
        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("src/main/connectionConfig.properties"));

            this.user = properties.getProperty("user");
            this.password = properties.getProperty("password");
            this.database = properties.getProperty("database");
            this.server = properties.getProperty("server");
        }
        catch (Exception e){
            System.out.println("Could not read properties from properties file.");
        }
    }

    public Connection makeConnection() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName(database);
        dataSource.setServerName(server);
        dataSource.setUser(user);
        dataSource.setPassword(password);

        Connection con = dataSource.getConnection();
        return con;
    }
}
