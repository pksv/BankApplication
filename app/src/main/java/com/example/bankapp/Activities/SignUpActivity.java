package com.example.bankapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankapp.Database.DataBaseHelper;
import com.example.bankapp.Database.User;
import com.example.bankapp.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.sql.SQLException;

public class SignUpActivity extends AppCompatActivity {

    private EditText etEmail, etAccount, etPhone, etPassword, etCPassword;
    private Button btnSignUp;
    private String email, password, cPassword, account, phone;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();
        setSupportActionBar(toolbar);

        btnSignUp.setOnClickListener(v -> {
            email = etEmail.getText().toString();
            password = etPassword.getText().toString();
            cPassword = etCPassword.getText().toString();
            account = etAccount.getText().toString();
            phone = etPhone.getText().toString();

            if (email.isEmpty()) {
                Toast.makeText(this, "Please fill Email Id", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid Email Id", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Please fill password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (password.length() < 6) {
                Toast.makeText(this, "Password should be minimum 6 characters", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!TextUtils.equals(password, cPassword)) {
                Toast.makeText(this, "Password and confirm password doesn't match", Toast.LENGTH_SHORT).show();
                return;
            }
            if (phone.isEmpty()) {
                Toast.makeText(this, "Please fill phone number", Toast.LENGTH_SHORT).show();
                return;
            }

            if (phone.length() != 10 || !phone.matches("[0-9]+")) {
                Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                return;
            }

            if (account.isEmpty()) {
                Toast.makeText(this, "Please enter account number", Toast.LENGTH_SHORT).show();
                return;
            }

            // TODO: 22-04-2021 duplicate copies check
            User user = new User();
            user.setEmail(email);
            user.setAccountNo(account);
            user.setPassword(password);
            user.setPhoneNumber(phone);

            try {
                DataBaseHelper.getInstance().getUserDAO().createOrUpdate(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(SignUpActivity.this, UserActivity.class);
            startActivity(intent);

        });
    }

    private void initViews() {
        etAccount = findViewById(R.id.etAccount);
        etPassword = findViewById(R.id.etPassword);
        etCPassword = findViewById(R.id.etConfirmPassword);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        btnSignUp = findViewById(R.id.btnSignUp);
        toolbar = findViewById(R.id.toolbar);
    }
}