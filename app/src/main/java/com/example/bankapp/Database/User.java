package com.example.bankapp.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@DatabaseTable(tableName = "user")
public class User implements Serializable {

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String BSR_BAL = "bsr_bal";

    public static final String EMAIL = "email";

    public static final String PASSWORD = "password";

    public static final String ACCOUNT_NO = "account_no";

    public static final String PHONE_NUMBER = "phone_number";

    public static final String CREATED_DATE = "created_date";

    public static final String UPDATED_DATE = "updated_date";

    @DatabaseField(id = true, columnName = ID)
    private String id = UUID.randomUUID().toString();

    @DatabaseField(columnName = NAME, canBeNull = false)
    private String name;

    @DatabaseField(columnName = BSR_BAL, canBeNull = false)
    private double bsrBal = 0;

    @DatabaseField(columnName = EMAIL, canBeNull = false)
    private String email;

    @DatabaseField(columnName = PASSWORD, canBeNull = false)
    private String password;

    @DatabaseField(columnName = PHONE_NUMBER, canBeNull = false)
    private String phoneNumber;

    @DatabaseField(columnName = ACCOUNT_NO, canBeNull = false)
    private String accountNo;

    @DatabaseField(columnName = CREATED_DATE, canBeNull = false)
    private Date createdDate = new Date();

    @DatabaseField(columnName = UPDATED_DATE, canBeNull = false)
    private Date updatedDate = new Date();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBsrBal() {
        return bsrBal;
    }

    public void setBsrBal(double bsrBal) {
        this.bsrBal = bsrBal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
