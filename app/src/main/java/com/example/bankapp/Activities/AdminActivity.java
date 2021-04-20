package com.example.bankapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.bankapp.R;
import com.google.android.material.appbar.MaterialToolbar;

public class AdminActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void onBackPressed() {

    }
}