package com.example.bankapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankapp.Adaptors.LoanAdapter;
import com.example.bankapp.Database.DataBaseHelper;
import com.example.bankapp.Database.LoanData;
import com.example.bankapp.Database.SharedPreferences.LoginPreferences;
import com.example.bankapp.Database.User;
import com.example.bankapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.SQLException;

public class LoanActivity extends AppCompatActivity {

    private Spinner loanTypeSpinner;
    private Button btnApply;
    private String value;
    private EditText etLoanAmt, etPeriod;
    private RecyclerView recyclerView;
    private BottomNavigationView bottomNavigationView;
    private ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);
        initViews();
        initBottomNavMenu();
        User user = DataBaseHelper.getInstance().getUserDAO().findByEmail(LoginPreferences.getInstance().getUserId());

        String[] loanType = getResources().getStringArray(R.array.loan_type);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, loanType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        loanTypeSpinner.setAdapter(adapter);
        loanTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getId() == R.id.loanTypeSpinner) {
                    value = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                value = null;
            }
        });

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        btnApply.setOnClickListener(v -> {
            String loanAmt, period;
            loanAmt = etLoanAmt.getText().toString();
            period = etPeriod.getText().toString();
            if (value.isEmpty()) {
                Toast.makeText(this, "Please Select Loan Type", Toast.LENGTH_SHORT).show();
                return;
            }
            if (loanAmt.isEmpty()) {
                Toast.makeText(this, "Please Enter Loan Amount", Toast.LENGTH_SHORT).show();
                return;
            }
            if (period.isEmpty()) {
                Toast.makeText(this, "Please Enter the Period", Toast.LENGTH_SHORT).show();
                return;
            }

            user.setBsrBal(user.getBsrBal() + Double.parseDouble(loanAmt));
            LoanData loanData = new LoanData();
            loanData.setLoanAmt(Double.parseDouble(loanAmt));
            loanData.setLoanType(value);
            loanData.setPeriod(Integer.parseInt(period));
            loanData.setUserId(user.getId());

            try {
                DataBaseHelper.getInstance().getLoanDAO().createOrUpdate(loanData);
                DataBaseHelper.getInstance().getUserDAO().createOrUpdate(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this)
                    .setTitle("Alert")
                    .setMessage("Loan Granted!!!")
                    .setPositiveButton("Okay", (dialogInterface, i) -> {
                        Intent intent = new Intent(this, UserHome.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    });
            builder.create().show();
        });

        LoanAdapter lAdapter = new LoanAdapter(this, DataBaseHelper.getInstance().getLoanDAO().findByUserId(user.getId()));
        recyclerView.setAdapter(lAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initBottomNavMenu() {
        bottomNavigationView.setSelectedItemId(R.id.loan);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.insurance:
                    Intent insuranceIntent = new Intent(this, InsuranceActivity.class);
                    insuranceIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(insuranceIntent);
                    break;
                case R.id.transactionHistory:
                    Intent sendMoneyIntent = new Intent(this, TransactionActivity.class);
                    sendMoneyIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(sendMoneyIntent);
                    break;
                case R.id.home:
                    Intent homeIntent = new Intent(this, UserHome.class);
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(homeIntent);
                    break;
                default:
                    break;
            }
            return false;
        });
    }

    private void initViews() {
        loanTypeSpinner = findViewById(R.id.loanTypeSpinner);
        btnApply = findViewById(R.id.btnApply);
        etLoanAmt = findViewById(R.id.etLoanAmt);
        etPeriod = findViewById(R.id.etPeriod);
        recyclerView = findViewById(R.id.recyclerView);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        logout = findViewById(R.id.logout);
    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(this, UserHome.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homeIntent);
    }
}