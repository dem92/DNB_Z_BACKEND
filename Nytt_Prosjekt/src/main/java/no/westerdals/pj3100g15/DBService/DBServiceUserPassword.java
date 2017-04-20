package no.westerdals.pj3100g15.DBService;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import no.westerdals.pj3100g15.ORM.UserPassword;
import no.westerdals.pj3100g15.ServerLogging.WriteLogg;

import java.sql.SQLException;
import java.util.List;

public class DBServiceUserPassword {
    /**
     * Returns an array with the password in hashed format(MD5) and the plaintext password to be compared with the submitted password.
     * This is UNSECURE!!!
     * @param birthdayNumber is used to find the correct password associated with the customer with the given birthdaynumber.
     * @return an Array with the hashed password and the password in plain text.
     */
    public static String[] getPassword(String birthdayNumber) {
        DBServiceConnection.makeConnection();

        try {
            Dao<UserPassword, String> passwordDao = DaoManager.createDao(DBServiceConnection.connectionSource, UserPassword.class);
            List<UserPassword> password = passwordDao.queryForEq("Foedselsnummer", birthdayNumber);

            if (password.size() != 1)
                return null;

            return new String[]{password.get(0).getPasswordHash(), password.get(0).getPasswordPlain()};
        } catch (SQLException e) {
            WriteLogg.writeLogg(e);
            e.printStackTrace();
        }
        return null;
    }
}
