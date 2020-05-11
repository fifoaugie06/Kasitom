package com.example.kasitom.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.kasitom.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

public class ChangeLevelActivity extends AppCompatActivity {
    Button btnStart_1, btnStart_2, btnStart_3, btnStart_4, btnStart_5, btnStart_6, btnStart_7;
    private DatabaseReference database;
    ArrayList<String> datasLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_level);

        initView();
        actionClick();

        database = FirebaseDatabase.getInstance().getReference();
        database.child("Quiz").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                datasLevel = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    datasLevel.add(noteDataSnapshot.getKey());

                }
                if (datasLevel.contains("Level A - 1")){
                    btnStart_1.setEnabled(true);
                    ConstraintLayout coomingsoon_1 = findViewById(R.id.coomingsoon_1);
                    coomingsoon_1.setVisibility(View.GONE);
                }
                if (datasLevel.contains("Level A - 2")){
                    btnStart_2.setEnabled(true);
                    ConstraintLayout coomingsoon_2 = findViewById(R.id.coomingsoon_2);
                    coomingsoon_2.setVisibility(View.GONE);
                }
                if (datasLevel.contains("Level A - 3")){
                    btnStart_3.setEnabled(true);
                    ConstraintLayout coomingsoon_3 = findViewById(R.id.coomingsoon_3);
                    coomingsoon_3.setVisibility(View.GONE);
                }
                if (datasLevel.contains("Level A - 4")){
                    btnStart_4.setEnabled(true);
                    ConstraintLayout coomingsoon_4 = findViewById(R.id.coomingsoon_4);
                    coomingsoon_4.setVisibility(View.GONE);
                }
                if (datasLevel.contains("Level A - 5")){
                    btnStart_5.setEnabled(true);
                    ConstraintLayout coomingsoon_5 = findViewById(R.id.coomingsoon_5);
                    coomingsoon_5.setVisibility(View.GONE);
                }
                if (datasLevel.contains("Level A - 6")){
                    btnStart_6.setEnabled(true);
                    ConstraintLayout coomingsoon_6 = findViewById(R.id.coomingsoon_6);
                    coomingsoon_6.setVisibility(View.GONE);
                }
                if (datasLevel.contains("Level A - 7")){
                    btnStart_7.setEnabled(true);
                    ConstraintLayout coomingsoon_7 = findViewById(R.id.coomingsoon_7);
                    coomingsoon_7.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        getSupportActionBar().hide();
    }

    private void actionClick() {
        btnStart_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeLevelActivity.this, QuizActivity.class);
                intent.putExtra("change_level", "Level A - 1");
                startActivity(intent);
            }
        });

        btnStart_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeLevelActivity.this, QuizActivity.class);
                intent.putExtra("change_level", "Level A - 2");
                startActivity(intent);
            }
        });

        btnStart_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeLevelActivity.this, QuizActivity.class);
                intent.putExtra("change_level", "Level A - 3");
                startActivity(intent);
            }
        });

        btnStart_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeLevelActivity.this, QuizActivity.class);
                intent.putExtra("change_level", "Level A - 4");
                startActivity(intent);
            }
        });

        btnStart_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeLevelActivity.this, QuizActivity.class);
                intent.putExtra("change_level", "Level A - 5");
                startActivity(intent);
            }
        });

        btnStart_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeLevelActivity.this, QuizActivity.class);
                intent.putExtra("change_level", "Level A - 6");
                startActivity(intent);
            }
        });

        btnStart_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChangeLevelActivity.this, QuizActivity.class);
                intent.putExtra("change_level", "Level A - 7");
                startActivity(intent);
            }
        });
    }

    private void initView() {
        btnStart_1 = findViewById(R.id.btnStart_1);
        btnStart_2 = findViewById(R.id.btnStart_2);
        btnStart_3 = findViewById(R.id.btnStart_3);
        btnStart_4 = findViewById(R.id.btnStart_4);
        btnStart_5 = findViewById(R.id.btnStart_5);
        btnStart_6 = findViewById(R.id.btnStart_6);
        btnStart_7 = findViewById(R.id.btnStart_7);

    }
}
