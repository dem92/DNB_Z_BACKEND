package no.westerdals.pj3100g15.ORM;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.List;

public class DBServiceAccount {
    private static ConnectionSource connectionSource = DBConnector.makeConnection();

    public String saldo(String accountNumber)throws SQLException{
        new Account();
        Dao<Account, String> saldoStringDao= DaoManager.createDao(connectionSource, Account.class);
        List<no.westerdals.pj3100g15.ORM.Account> account = saldoStringDao.queryForEq("Kontonummer",accountNumber);
        return account.get(0).getKroner().toString();
    }


}
