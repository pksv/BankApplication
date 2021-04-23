package com.example.bankapp.Database.DAO;

import com.example.bankapp.Database.InsuranceData;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.ArrayList;

public class InsuranceDAO extends BaseDaoImpl<InsuranceData, String> {
    public InsuranceDAO(ConnectionSource connectionSource, Class<InsuranceData> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public ArrayList<InsuranceData> findByUserId(String id) {
        try {
            return (ArrayList<InsuranceData>) queryBuilder().where().eq(InsuranceData.USER_ID, id).query();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
