package com.example.bankapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.bankapp.Database.DataBaseHelper;
import com.example.bankapp.Database.SharedPreferences.AdminPreferences;
import com.example.bankapp.Database.Transaction;
import com.example.bankapp.Database.User;
import com.example.bankapp.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.sql.SQLException;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etNewRate, etAmount, etAccountNumber;
    private Button btnAddMoney, btnRateUpdate, btnDeductMoney;
    private MaterialToolbar toolbar;
    private ImageView logout;
    private TextView adminBal;

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
            etNewRate.setText("");
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Alert")
                    .setMessage("New Rate Updated")
                    .setPositiveButton("Okay", (dialogInterface, i) -> {
                    });
            builder.create().show();
        });

        btnDeductMoney.setOnClickListener(this);
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
        btnDeductMoney = findViewById(R.id.btnDeductMoney);
        etNewRate = findViewById(R.id.etNewRate);
        etAccountNumber = findViewById(R.id.etAccountNumber);
        etAmount = findViewById(R.id.etAmount);
        adminBal = findViewById(R.id.adminBal);
        adminBal.setText(String.format("Balance : %s", AdminPreferences.getInstance().getBal()));
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please Logout", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == btnAddMoney.getId() || v.getId() == btnDeductMoney.getId()) {
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
            if (v.getId() == btnDeductMoney.getId()) {
                if (Double.parseDouble(amt) > user.getBsrBal()) {
                    Toast.makeText(this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                    return;
                }
                user.setBsrBal(user.getBsrBal() - Double.parseDouble(amt));
                AdminPreferences.getInstance().setBal(AdminPreferences.getInstance().getBal() + Double.parseDouble(amt));
                Transaction transaction = new Transaction();
                transaction.setSender(user.getId());
                transaction.setReceiver("ADMIN");
                transaction.setAmount(Double.parseDouble(amt));
                transaction.setSuccessful(true);
                try {
                    DataBaseHelper.getInstance().getUserDAO().createOrUpdate(user);
                    DataBaseHelper.getInstance().getTransactionDAO().createOrUpdate(transaction);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                adminBal.setText(String.format("Balance : %s", AdminPreferences.getInstance().getBal()));
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setTitle("Alert")
                        .setMessage("Amount Deducted")
                        .setPositiveButton("Okay", (dialogInterface, i) -> {
                        });
                builder.create().show();
            } else {
                user.setBsrBal(user.getBsrBal() + Double.parseDouble(amt));
                AdminPreferences.getInstance().setBal(AdminPreferences.getInstance().getBal() - Double.parseDouble(amt));
                Transaction transaction = new Transaction();
                transaction.setReceiver(user.getId());
                transaction.setSender("ADMIN");
                transaction.setAmount(Double.parseDouble(amt));
                transaction.setSuccessful(true);
                try {
                    DataBaseHelper.getInstance().getUserDAO().createOrUpdate(user);
                    DataBaseHelper.getInstance().getTransactionDAO().createOrUpdate(transaction);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        etAccountNumber.setText("");
        etAmount.setText("");
        adminBal.setText(String.format("Balance : %s", AdminPreferences.getInstance().getBal()));
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Amount Sent")
                .setPositiveButton("Okay", (dialogInterface, i) -> {
                });
        builder.create().show();
    }
}