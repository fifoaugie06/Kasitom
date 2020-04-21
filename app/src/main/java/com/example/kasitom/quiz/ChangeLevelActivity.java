package com.example.kasitom.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kasitom.R;

public class ChangeLevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_level);

        getSupportActionBar().hide();
    }
}
