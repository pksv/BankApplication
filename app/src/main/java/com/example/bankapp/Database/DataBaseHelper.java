package com.example.bankapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.bankapp.Application;
import com.example.bankapp.Database.DAO.InsuranceDAO;
import com.example.bankapp.Database.DAO.LoanDAO;
import com.example.bankapp.Database.DAO.TransactionDAO;
import com.example.bankapp.Database.DAO.UserDAO;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static DataBaseHelper dataBaseHelper;
    private static UserDAO userDAO;
    private static InsuranceDAO insuranceDAO;
    private static LoanDAO loanDAO;
    private static TransactionDAO transactionDAO;


    public DataBaseHelper(Context context, String databaseName) {
        super(context, databaseName, null, DATABASE_VERSION);
    }


    public static DataBaseHelper getInstance() {
        if (dataBaseHelper == null) {
            dataBaseHelper = new DataBaseHelper(Application.getInstance(), "bankApplication.db");
            dataBaseHelper.getWritableDatabase();
            userDAO = dataBaseHelper.getUserDAO();
            insuranceDAO = dataBaseHelper.getInsuranceDAO();
            loanDAO = dataBaseHelper.getLoanDAO();
            transactionDAO = dataBaseHelper.getTransactionDAO();
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

    public InsuranceDAO getInsuranceDAO() {
        if (insuranceDAO == null) {
            try {
                insuranceDAO = new InsuranceDAO(connectionSource, InsuranceData.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return insuranceDAO;
    }

    public LoanDAO getLoanDAO() {
        if (loanDAO == null) {
            try {
                loanDAO = new LoanDAO(connectionSource, LoanData.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return loanDAO;
    }

    public TransactionDAO getTransactionDAO() {
        if (transactionDAO == null) {
            try {
                transactionDAO = new TransactionDAO(connectionSource, Transaction.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return transactionDAO;
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, User.class);
            TableUtils.createTable(connectionSource, InsuranceData.class);
            TableUtils.createTable(connectionSource, LoanData.class);
            TableUtils.createTable(connectionSource, Transaction.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }
}
