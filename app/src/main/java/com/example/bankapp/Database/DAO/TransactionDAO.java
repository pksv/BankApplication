package com.example.bankapp.Database.DAO;

import com.example.bankapp.Database.Transaction;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;

public class TransactionDAO extends BaseDaoImpl<Transaction, String> {

    public TransactionDAO(ConnectionSource connectionSource, Class<Transaction> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public ArrayList<Transaction> findBySenderReceiver(String id) {
        try {
            return (ArrayList<Transaction>) queryBuilder().where().eq(Transaction.SENDER, id).or().eq(Transaction.RECEIVER, id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

//    public ArrayList<Transaction> findByReceiver(String id) {
//        try {
//            return (ArrayList<Transaction>) queryBuilder().where().eq(Transaction.RECEIVER, id).query();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
}
