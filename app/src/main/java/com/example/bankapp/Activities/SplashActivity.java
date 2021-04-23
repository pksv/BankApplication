package com.example.bankapp.Activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.bankapp.Database.SharedPreferences.LoginPreferences;
import com.example.bankapp.R;

public class SplashActivity extends AppCompatActivity {

    private final Handler mWaitHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if ((ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
        }

        mWaitHandler.postDelayed(() -> {
            if (LoginPreferences.getInstance().getUserId().isEmpty()) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } else if (LoginPreferences.getInstance().getUserId().equals("pksv@admin")) {
                Intent intent = new Intent(this, AdminActivity.class);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(this, UserHome.class);
                startActivity(intent);
                finish();
            }

        }, 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWaitHandler.removeCallbacksAndMessages(null);
    }
}
