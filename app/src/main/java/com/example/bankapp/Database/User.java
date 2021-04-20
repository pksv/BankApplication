package com.example.bankapp.Database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User implements Parcelable {

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int bal;
    private String email, accountNo, phone, password;

    public User(int bal, String email, String accountNo, String phone, String password) {
        this.bal = bal;
        this.email = email;
        this.accountNo = accountNo;
        this.phone = phone;
        this.password = password;
    }

    protected User(Parcel in) {
        id = in.readInt();
        bal = in.readInt();
        email = in.readString();
        accountNo = in.readString();
        phone = in.readString();
        password = in.readString();
    }

    public int getBal() {
        return bal;
    }

    public void setBal(int bal) {
        this.bal = bal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "bal=" + bal +
                ", email='" + email + '\'' +
                ", accountNo='" + accountNo + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(bal);
        dest.writeString(email);
        dest.writeString(accountNo);
        dest.writeString(phone);
        dest.writeString(password);
    }
}
