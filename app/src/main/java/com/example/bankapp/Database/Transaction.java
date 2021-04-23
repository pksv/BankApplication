package com.example.bankapp.Database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;
import java.util.UUID;

@DatabaseTable(tableName = "transaction")
public class Transaction {
    public static final String ID = "id";

    public static final String SENDER = "sender";

    public static final String RECEIVER = "receiver";

    public static final String AMOUNT = "amount";

    public static final String SUCCESSFUL = "successful";

    public static final String CREATED_DATE = "created_date";

    public static final String UPDATED_DATE = "updated_date";

    @DatabaseField(id = true, columnName = ID)
    private String id = UUID.randomUUID().toString();

    @DatabaseField(canBeNull = false, columnName = SENDER)
    private String sender;

    @DatabaseField(canBeNull = false, columnName = RECEIVER)
    private String receiver;

    @DatabaseField(canBeNull = false, columnName = AMOUNT)
    private double amount;

    @DatabaseField(canBeNull = false, columnName = SUCCESSFUL)
    private boolean successful = false;

    @DatabaseField(columnName = CREATED_DATE, canBeNull = false)
    private Date createdDate = new Date();

    @DatabaseField(columnName = UPDATED_DATE, canBeNull = false)
    private Date updatedDate = new Date();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
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
