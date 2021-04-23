package com.example.bankapp.Database.DAO;

import com.example.bankapp.Database.Transaction;
import com.example.bankapp.Database.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionDAO extends BaseDaoImpl<Transaction, String> {

    public TransactionDAO(ConnectionSource connectionSource, Class<Transaction> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public ArrayList<Transaction> findBySender(String id) {
        try {
            return (ArrayList<Transaction>) queryBuilder().where().eq(User.ID, id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Transaction> findByReceiver(String id) {
        try {
            return (ArrayList<Transaction>) queryBuilder().where().eq(User.ID, id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
