package com.example.back4app.userregistrationexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import static com.example.back4app.userregistrationexample.PreferenceKey.ISFIRST;
import static com.example.back4app.userregistrationexample.PreferenceKey.REGISTARED;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPref.init(getApplicationContext());

        if (!(SharedPref.read(REGISTARED).equals("true"))) {

            SharedPref.write(REGISTARED, "false");
            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
            startActivity(intent);

        } else {
            Intent intent = new Intent(SplashActivity.this, ProfileActivity.class);
            startActivity(intent);
        }


    }
}
