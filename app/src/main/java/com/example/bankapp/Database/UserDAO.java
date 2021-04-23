package com.example.bankapp.Database;


import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class UserDAO extends BaseDaoImpl<User, String> {

    protected UserDAO(ConnectionSource connectionSource, Class<User> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public User findByEmail(String email) {
        try {
            return queryBuilder().where().eq(User.EMAIL, email).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User findByAccount(String account) {
        try {
            return queryBuilder().where().eq(User.ACCOUNT_NO, account).queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
