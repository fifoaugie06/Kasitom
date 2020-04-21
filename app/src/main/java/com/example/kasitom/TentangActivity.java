package com.example.kasitom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class TentangActivity extends AppCompatActivity {
    private TextView tv_Version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_activity);

        getSupportActionBar().setTitle(R.string.menu_tentang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tv_Version = findViewById(R.id.tv_version);

        tv_Version.setText("v "+BuildConfig.VERSION_NAME);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
