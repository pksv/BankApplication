package com.example.bankapp.Database.DAO;

import com.example.bankapp.Database.LoanData;
import com.example.bankapp.Database.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class LoanDAO extends BaseDaoImpl<LoanData, String> {
    public LoanDAO(ConnectionSource connectionSource, Class<LoanData> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public LoanData findByUserId(String id) {
        try {
            return queryBuilder().where().eq(User.ID, id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
