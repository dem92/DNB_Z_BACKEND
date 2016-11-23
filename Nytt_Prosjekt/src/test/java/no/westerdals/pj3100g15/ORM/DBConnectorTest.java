package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.support.ConnectionSource;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class DBConnectorTest {

    @Test
    public void testNotNullMakeConnection() {
        //Assert
        assertNotNull(DBConnector.makeConnection());
    }

    @Test
    public void testMakeConnectionReadsFromPropertiesfile() throws IOException{
        //Arrange
        FileReader fileReader = new FileReader("db.properties");
        Properties properties = new Properties();
        properties.load(fileReader);

        //Act
        String userName = properties.getProperty("db.user");

        //Assert
        assertNotNull(userName);
    }

    @Test
    public void testConnectionName(){
        //Arrange
        String connectionName = "com.j256.ormlite.jdbc.JdbcConnectionSource";
        //Act
        String actualConnectionName = DBConnector.makeConnection().toString().substring(0,42);
        //Assert
        assertEquals(connectionName,actualConnectionName);
    }

    @Test //Denne testen sjekker om to connections kan være det samme
    public void testConnectToDatabase()throws IOException{
        //Arrange
        FileReader fileReader = new FileReader("db.properties");
        Properties properties = new Properties();
        properties.load(fileReader);
        String databaseUrl = properties.getProperty("db.databaseUrl");

        //Arrange
        assertNotSame(DBConnector.makeConnection(),DBConnector.connectToDatabase( "HenrikTheThief", "XXX_123SQLInjectionHackker_XXX",databaseUrl));
    }

    @Test(expected = AssertionError.class) //Tests if the method returns null if the URL is wrong.
    public void testMakeConnectionIfNotValid(){
        ConnectionSource connectionSource = DBConnector.connectToDatabase("","NoeFeil","jdbc:mysql://www.noeFeil.no");
        assertNull(connectionSource);
    }



}
