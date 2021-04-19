package com.example.bankapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankapp.R;

public class MainActivity extends AppCompatActivity {

    private TextView signUp;
    private EditText etEmail, etPassword;
    private Button login;
    private String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            email = etEmail.getText().toString();
            password = etPassword.getText().toString();
            Intent intent;
            if (email.equals("pksv@admin") && password.equals("pksv")) {
                intent = new Intent(MainActivity.this, AdminActivity.class);
            } else {
                intent = new Intent(MainActivity.this, UserActivity.class);
            }
            startActivity(intent);
        });

    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnSignUp);
        signUp = findViewById(R.id.tvSignUp);
    }
}