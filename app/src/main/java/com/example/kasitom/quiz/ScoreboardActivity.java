package com.example.kasitom.quiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.kasitom.R;
import com.example.kasitom.model.dataScoreBoard;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreboardActivity extends AppCompatActivity {
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private ArrayList<dataScoreBoard> dataScoreBoards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        DatabaseReference database;
        RecyclerView.LayoutManager layoutManager;

        getSupportActionBar().setTitle(R.string.menu_papanSkor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvView = findViewById(R.id.rv_scoreboard);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);

        database = FirebaseDatabase.getInstance().getReference();

        database.child("scoreboard").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                dataScoreBoards = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    dataScoreBoard dataScoreBoard = noteDataSnapshot.getValue(dataScoreBoard.class);
                    dataScoreBoard.setKey(noteDataSnapshot.getKey());

                    dataScoreBoards.add(dataScoreBoard);
                }

                // descending data
                Collections.sort(dataScoreBoards, new Comparator<dataScoreBoard>() {
                    @Override
                    public int compare(dataScoreBoard lhs, dataScoreBoard rhs) {
                        if (Float.parseFloat(lhs.getNilai()) > Float.parseFloat(rhs.getNilai())) {
                            return -1;
                        } else {
                            return 0;
                        }
                    }
                });

                adapter = new AdapterScoreboard(dataScoreBoards);
                rvView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.i("hm", databaseError.getDetails() + " " + databaseError.getMessage());
            }
        });

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
