package com.example.bankapp.Database.DAO;

import com.example.bankapp.Database.InsuranceData;
import com.example.bankapp.Database.User;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class InsuranceDAO extends BaseDaoImpl<InsuranceData, String> {
    public InsuranceDAO(ConnectionSource connectionSource, Class<InsuranceData> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public InsuranceData findByUserId(String id) {
        try {
            return queryBuilder().where().eq(User.ID, id).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
