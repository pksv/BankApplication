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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankapp.Database.DataBaseHelper;
import com.example.bankapp.Database.SharedPreferences.LoginPreferences;
import com.example.bankapp.Database.User;
import com.example.bankapp.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserHome extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private ImageView logout;
    private TextView tvBsrBal, tvAccount, tvBal;
    private BottomNavigationView bottomNavigationView;
    private Spinner currencySpinner;
    private Button send;
    private EditText receiverAcc, sendingAmount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        initViews();
        setSupportActionBar(toolbar);
        initBottomNavMenu();

        String name = LoginPreferences.getInstance().getName();
        String email = LoginPreferences.getInstance().getUserId();
        User user = DataBaseHelper.getInstance().getUserDAO().findByEmail(email);
        double bal = user.getBsrBal();
        String[] currency = getResources().getStringArray(R.array.currency);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, currency);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        currencySpinner.setAdapter(adapter);

        currencySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getId() == R.id.currencySpinner) {
                    String value = parent.getItemAtPosition(position).toString();
                    switch (value) {
                        case "INR":
                            tvBal.setText(String.format("Balance : ₹%s", (bal * 100 * 75.11)));
                            break;
                        case "JPY":
                            tvBal.setText(String.format("Balance : ¥%s", (bal * 100 * 108.15)));
                            break;
                        case "GBP":
                            tvBal.setText(String.format("Balance : £%s", (bal * 100 * 0.72)));
                            break;
                        case "AED":
                            tvBal.setText(String.format("Balance : د.إ%s", (bal * 100 * 3.67)));
                            break;
                        case "AUD":
                            tvBal.setText(String.format("Balance : A$%s", (bal * 100 * 1.29)));
                            break;
                        default:
                            tvBal.setText(String.format("Balance : $%s", (bal * 100)));
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                tvBal.setText(String.format("Balance : $%s", (bal * 100)));
            }
        });

        toolbar.setTitle("Welcome, " + name);
        tvAccount.setText(String.format("Account Number : %s", user.getAccountNo()));
        tvBsrBal.setText(String.format("BSR Balance : %s", user.getBsrBal()));

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });


    }

    private void initBottomNavMenu() {
        bottomNavigationView.setSelectedItemId(R.id.home);
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
                case R.id.loan:
                    Intent loanIntent = new Intent(this, LoanActivity.class);
                    loanIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(loanIntent);
                    break;
                default:
                    break;
            }
            return false;
        });
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        logout = findViewById(R.id.logout);
        tvAccount = findViewById(R.id.tvAccount);
        tvBsrBal = findViewById(R.id.tvBsrBal);
        tvBal = findViewById(R.id.tvBal);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        currencySpinner = findViewById(R.id.currencySpinner);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Please Logout", Toast.LENGTH_SHORT).show();
    }
}