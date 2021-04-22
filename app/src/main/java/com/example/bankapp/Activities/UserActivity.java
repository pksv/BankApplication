package com.example.bankapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bankapp.R;
import com.google.android.material.appbar.MaterialToolbar;

public class UserActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;
    private ImageView logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        toolbar = findViewById(R.id.toolbar);
        logout = findViewById(R.id.logout);
        setSupportActionBar(toolbar);
        logout.setOnClickListener(v -> {
            Intent intent = new Intent(UserActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onBackPressed() {
        
    }
}