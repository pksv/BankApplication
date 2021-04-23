package com.example.bankapp.Database.DAO;

import com.example.bankapp.Database.LoanData;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;

public class LoanDAO extends BaseDaoImpl<LoanData, String> {
    public LoanDAO(ConnectionSource connectionSource, Class<LoanData> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public ArrayList<LoanData> findByUserId(String id) {
        try {
            return (ArrayList<LoanData>) queryBuilder().where().eq(LoanData.USER_ID, id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
