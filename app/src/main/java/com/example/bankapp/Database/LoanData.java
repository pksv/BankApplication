package com.example.bankapp.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.UUID;

@DatabaseTable(tableName = "loan_data")
public class LoanData {

    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String LOAN_AMT = "loan_amt";

    public static final String LOAN_TYPE = "loan_type";

    public static final String APPL_DATE = "appl_date";

    public static final String INTEREST = "interest";

    public static final String CLEAR_DATE = "clear_date";

    public static final String UPDATED_DATE = "updated_date";

    @DatabaseField(id = true, columnName = ID)
    private String id = UUID.randomUUID().toString();

    @DatabaseField(canBeNull = false, columnName = USER_ID)
    private String userId;

    @DatabaseField(canBeNull = false, columnName = LOAN_AMT)
    private double loanAmt;

    @DatabaseField(canBeNull = false, columnName = LOAN_TYPE)
    private String loanType;

    @DatabaseField(canBeNull = false, columnName = APPL_DATE)
    private Date applDate = new Date();

    @DatabaseField(canBeNull = false, columnName = INTEREST)
    private double interest;

    @DatabaseField(canBeNull = false, columnName = CLEAR_DATE)
    private Date clearDate;

    @DatabaseField(columnName = UPDATED_DATE, canBeNull = false)
    private Date updatedDate = new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public Date getApplDate() {
        return applDate;
    }

    public void setApplDate(Date applDate) {
        this.applDate = applDate;
    }

    public Date getClearDate() {
        return clearDate;
    }

    public void setClearDate(Date clearDate) {
        this.clearDate = clearDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public double getLoanAmt() {
        return loanAmt;
    }

    public void setLoanAmt(double loanAmt) {
        this.loanAmt = loanAmt;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}
