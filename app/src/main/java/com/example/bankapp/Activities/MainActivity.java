package com.example.bankapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.bankapp.Database.DataBaseHelper;
import com.example.bankapp.Database.SharedPreferences.LoginPreferences;
import com.example.bankapp.Database.User;
import com.example.bankapp.R;
import com.google.android.material.appbar.MaterialToolbar;

public class MainActivity extends AppCompatActivity {

    private TextView signUp;
    private EditText etEmail, etPassword;
    private Button login;
    private String email, password;
    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setSupportActionBar(toolbar);

        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }


        signUp.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        login.setOnClickListener(v -> {
            email = etEmail.getText().toString();
            password = etPassword.getText().toString();
            Intent intent;

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Enter Email ID", Toast.LENGTH_SHORT).show();
                return;
            }

            if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
                return;
            }

            if (email.equals("pksv@admin") && password.equals("pksv")) {
                LoginPreferences.getInstance().setUserId("pksv@admin");
                LoginPreferences.getInstance().setName("admin");
                intent = new Intent(MainActivity.this, AdminActivity.class);
            } else {
                User user = DataBaseHelper.getInstance().getUserDAO().findByEmail(email);
                if (user == null) {
                    Toast.makeText(this, "Invalid Email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.equals(user.getPassword(), password)) {
                    Toast.makeText(this, "Invalid Password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                LoginPreferences.getInstance().setUserId(email);
                LoginPreferences.getInstance().setName(user.getName());
                intent = new Intent(MainActivity.this, UserHome.class);
            }
            startActivity(intent);
            finish();
        });

    }

    private void initViews() {
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnSignUp);
        signUp = findViewById(R.id.tvSignUp);
        toolbar = findViewById(R.id.toolbar);
    }
}
