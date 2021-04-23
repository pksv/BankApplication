package com.example.bankapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankapp.Adaptors.TransactionAdapter;
import com.example.bankapp.Database.DataBaseHelper;
import com.example.bankapp.Database.SharedPreferences.LoginPreferences;
import com.example.bankapp.Database.Transaction;
import com.example.bankapp.Database.User;
import com.example.bankapp.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private ImageView logout;
    private RecyclerView recyclerView;
    private NestedScrollView svRecyclerView;
    private TextView noTransaction;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        initViews();
        setSupportActionBar(toolbar);
        initBottomNavMenu();

        logout.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        String email = LoginPreferences.getInstance().getUserId();
        User user = DataBaseHelper.getInstance().getUserDAO().findByEmail(email);
        ArrayList<Transaction> transactions = DataBaseHelper.getInstance().getTransactionDAO().findBySenderReceiver(user.getId());

        if (transactions == null) {
            svRecyclerView.setVisibility(View.GONE);
            noTransaction.setVisibility(View.VISIBLE);
            return;
        }
        TransactionAdapter tAdapter = new TransactionAdapter(this, transactions);
        recyclerView.setAdapter(tAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                case R.id.home:
                    Intent homeIntent = new Intent(this, UserHome.class);
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(homeIntent);
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
        noTransaction = findViewById(R.id.noTransaction);
        recyclerView = findViewById(R.id.recyclerView);
        svRecyclerView = findViewById(R.id.svRecyclerView);
    }

    @Override
    public void onBackPressed() {
        Intent homeIntent = new Intent(this, UserHome.class);
        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(homeIntent);
    }
}