package com.example.bankapp.Database.DAO;

import com.example.bankapp.Database.Transaction;
import com.example.bankapp.Database.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class TransactionDAO extends BaseDaoImpl<Transaction, String> {

    public TransactionDAO(ConnectionSource connectionSource, Class<Transaction> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public Transaction findBySender(String id) {
        try {
            return queryBuilder().where().eq(User.ID, id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Transaction findByReceiver(String id) {
        try {
            return queryBuilder().where().eq(User.ID, id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
