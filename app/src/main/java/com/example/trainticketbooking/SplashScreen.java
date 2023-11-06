package com.example.trainticketbooking;

import android.annotation.SuppressLint;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.busticketbooking.R;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {
    public static int timer=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
                finish();
            }
        },timer);
    }
}