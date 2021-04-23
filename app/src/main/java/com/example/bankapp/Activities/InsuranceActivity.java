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
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankapp.Adaptors.InsuranceAdapter;
import com.example.bankapp.Database.DataBaseHelper;
import com.example.bankapp.Database.InsuranceData;
import com.example.bankapp.Database.SharedPreferences.LoginPreferences;
import com.example.bankapp.Database.User;
import com.example.bankapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.sql.SQLException;
import java.util.ArrayList;

public class InsuranceActivity extends AppCompatActivity {

    private Spinner insuranceTypeSpinner;
    private TextView noInsuranceHistory;
    private Button btnApply;
    private String value;
    private EditText etInsuranceAmt, etNominee;
    private RecyclerView recyclerView;
    private NestedScrollView svRecyclerView;
    private BottomNavigationView bottomNavigationView;
    private ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insurance);
        initViews();
        initBottomNavMenu();
        User user = DataBaseHelper.getInstance().getUserDAO().findByEmail(LoginPreferences.getInstance().getUserId());

        String[] insuranceType = getResources().getStringArray(R.array.insurance_type);
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, insuranceType);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        insuranceTypeSpinner.setAdapter(adapter);
        insuranceTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getId() == R.id.insuranceTypeSpinner) {
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
            String insuranceAmt, nominee;
            insuranceAmt = etInsuranceAmt.getText().toString();
            nominee = etNominee.getText().toString();
            if (value.isEmpty()) {
                Toast.makeText(this, "Please Select Insurance Type", Toast.LENGTH_SHORT).show();
                return;
            }
            if (insuranceAmt.isEmpty()) {
                Toast.makeText(this, "Please Enter Insurance Amount", Toast.LENGTH_SHORT).show();
                return;
            }
            if (nominee.isEmpty()) {
                Toast.makeText(this, "Please Enter Your Nominee", Toast.LENGTH_SHORT).show();
                return;
            }

            double bsrBal = user.getBsrBal();

            if (Double.parseDouble(insuranceAmt) < bsrBal) {
                Toast.makeText(this, "Insufficient Balance", Toast.LENGTH_SHORT).show();
                return;
            }

            user.setBsrBal(user.getBsrBal() - Double.parseDouble(insuranceAmt));
            InsuranceData insuranceData = new InsuranceData();
            insuranceData.setInsuranceAmt(Double.parseDouble(insuranceAmt));
            insuranceData.setInsuranceType(value);
            insuranceData.setNominee(nominee);
            insuranceData.setUserId(user.getId());

            try {
                DataBaseHelper.getInstance().getInsuranceDAO().createOrUpdate(insuranceData);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(this, UserHome.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        });

        ArrayList<InsuranceData> insuranceDataList = DataBaseHelper.getInstance().getInsuranceDAO().findByUserId(user.getId());

        if (insuranceDataList == null) {
            svRecyclerView.setVisibility(View.GONE);
            noInsuranceHistory.setVisibility(View.VISIBLE);
            return;
        }
        InsuranceAdapter iAdapter = new InsuranceAdapter(this, insuranceDataList);
        recyclerView.setAdapter(iAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initViews() {
        insuranceTypeSpinner = findViewById(R.id.insuranceTypeSpinner);
        btnApply = findViewById(R.id.btnApply);
        etInsuranceAmt = findViewById(R.id.etInsuranceAmt);
        etNominee = findViewById(R.id.etNominee);
        recyclerView = findViewById(R.id.recyclerView);
        svRecyclerView = findViewById(R.id.svRecyclerView);
        noInsuranceHistory = findViewById(R.id.noInsuranceHistory);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        logout = findViewById(R.id.logout);
    }

    private void initBottomNavMenu() {
        bottomNavigationView.setSelectedItemId(R.id.insurance);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    Intent insuranceIntent = new Intent(this, UserHome.class);
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InsuranceActivity.this, UserHome.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}