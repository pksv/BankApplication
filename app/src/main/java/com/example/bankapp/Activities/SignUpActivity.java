package com.example.bankapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankapp.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText etEmail, etAccount, etPhone, etPassword, etCPassword;
    private Button btnSignUp;
    private String email, password, cPassword,account, phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initViews();


        btnSignUp.setOnClickListener(v -> {
            email = etEmail.getText().toString();
            password = etPassword.getText().toString();
            cPassword = etCPassword.getText().toString();
            account = etAccount.getText().toString();
            phone = etPhone.getText().toString();
            if (!email.isEmpty() && !password.isEmpty() && !cPassword.isEmpty()
                    && password.equals(cPassword) && !account.isEmpty() && !phone.isEmpty()) {
                Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                etPhone.setText("Warning");
            }
        });
    }

    private void initViews() {
        etAccount = findViewById(R.id.etAccount);
        etPassword = findViewById(R.id.etPassword);
        etCPassword = findViewById(R.id.etConfirmPassword);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        btnSignUp = findViewById(R.id.btnSignUp);
    }
}