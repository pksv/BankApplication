package com.example.bankapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bankapp.Application;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static DataBaseHelper dataBaseHelper;
    private static UserDAO userDAO;

    public DataBaseHelper(Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION);
    }


    public static DataBaseHelper getInstance() {
        if (dataBaseHelper == null) {
            dataBaseHelper = new DataBaseHelper(Application.getInstance(), "bankApplication.db");
            dataBaseHelper.getWritableDatabase();
            userDAO = dataBaseHelper.getUserDAO();
        }
        return dataBaseHelper;
    }

    public UserDAO getUserDAO() {
        if (userDAO == null) {
            try {
                userDAO = new UserDAO(connectionSource, User.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userDAO;
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
