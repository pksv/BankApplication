package com.example.bankapp.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.UUID;

@DatabaseTable(tableName = "insurance")
public class InsuranceData {

    public static final String ID = "id";

    public static final String USER_ID = "user_id";

    public static final String INSURANCE_AMT = "insurance_amt";

    public static final String INSURANCE_TYPE = "insurance_type";

    public static final String APPL_DATE = "appl_date";

    public static final String MATURE_DATE = "mature_date";

    public static final String MATURE_AMT = "mature_amt";

    public static final String NOMINEE = "nominee";

    public static final String UPDATED_DATE = "updated_date";

    @DatabaseField(id = true, columnName = ID)
    private final String id = UUID.randomUUID().toString();

    @DatabaseField(canBeNull = false, columnName = APPL_DATE)
    private final Date applDate = new Date();

    @DatabaseField(columnName = UPDATED_DATE, canBeNull = false)
    private final Date updatedDate = new Date();

    @DatabaseField(canBeNull = false, columnName = USER_ID)
    private String userId;

    @DatabaseField(canBeNull = false, columnName = INSURANCE_AMT)
    private double insuranceAmt;

    @DatabaseField(canBeNull = false, columnName = INSURANCE_TYPE)
    private String insuranceType;

    @DatabaseField(canBeNull = false, columnName = NOMINEE)
    private String nominee;

    public String getId() {
        return id;
    }

    public Date getApplDate() {
        return applDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getInsuranceAmt() {
        return insuranceAmt;
    }

    public void setInsuranceAmt(double insuranceAmt) {
        this.insuranceAmt = insuranceAmt;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public String getNominee() {
        return nominee;
    }

    public void setNominee(String nominee) {
        this.nominee = nominee;
    }
}
