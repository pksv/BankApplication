package com.example.bankapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankapp.Database.AdminPreferences;
import com.example.bankapp.Database.DataBaseHelper;
import com.example.bankapp.Database.User;
import com.example.bankapp.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.sql.SQLException;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNewRate, etAmount, etAccountNumber;
    private Button btnAddMoney, btnRateUpdate, btnRemoveMoney;
    private MaterialToolbar toolbar;
    private ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        initViews();
        setSupportActionBar(toolbar);

        btnRateUpdate.setOnClickListener(v -> {
            String rate = etNewRate.getText().toString();
            if (rate.isEmpty()) {
                Toast.makeText(this, "Enter New Rate", Toast.LENGTH_SHORT).show();
                return;
            }
            AdminPreferences.getInstance().setBSRRate(Double.parseDouble(rate));
        });

        btnRemoveMoney.setOnClickListener(this);
        btnAddMoney.setOnClickListener(this);

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        logout = findViewById(R.id.logout);
        btnAddMoney = findViewById(R.id.btnAddMoney);
        btnRateUpdate = findViewById(R.id.btnRateUpdate);
        btnRemoveMoney = findViewById(R.id.btnRemoveMoney);
        etNewRate = findViewById(R.id.etNewRate);
        etAccountNumber = findViewById(R.id.etAccountNumber);
        etAmount = findViewById(R.id.etAmount);

    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please Logout", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnAddMoney.getId() || v.getId() == btnRemoveMoney.getId()) {
            String accNo = etAccountNumber.getText().toString();
            String amt = etAmount.getText().toString();
            if (accNo.isEmpty()) {
                Toast.makeText(this, "Enter Account Number", Toast.LENGTH_SHORT).show();
                return;
            }
            User user = DataBaseHelper.getInstance().getUserDAO().findByAccount(accNo);
            if (user == null) {
                Toast.makeText(this, "No Such User", Toast.LENGTH_SHORT).show();
                return;
            }
            if (amt.isEmpty()) {
                Toast.makeText(this, "Please Enter Amount", Toast.LENGTH_SHORT).show();
                return;
            }
            if (v.getId() == btnRemoveMoney.getId()) {
                if (Double.parseDouble(amt) > user.getBsrBal()) {
                    Toast.makeText(this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.setBsrBal(user.getBsrBal() - Double.parseDouble(amt));
                try {
                    DataBaseHelper.getInstance().getUserDAO().createOrUpdate(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                user.setBsrBal(user.getBsrBal() + Double.parseDouble(amt));
                try {
                    DataBaseHelper.getInstance().getUserDAO().createOrUpdate(user);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}