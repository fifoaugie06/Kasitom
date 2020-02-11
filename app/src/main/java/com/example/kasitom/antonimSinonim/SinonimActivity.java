package com.example.kasitom.antonimSinonim;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.kasitom.R;
import com.example.kasitom.model.dataKamus;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SinonimActivity extends AppCompatActivity {
    private RecyclerView rvView;
    private RecyclerView.Adapter adapter;
    private ArrayList<dataKamus> daftarSinonim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_antonim_sinonim);

        DatabaseReference database;
        RecyclerView.LayoutManager layoutManager;

        rvView = findViewById(R.id.rv_antonim);
        rvView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvView.setLayoutManager(layoutManager);
        getSupportActionBar().setTitle(R.string.sinonim);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final LinearLayout progressBar = findViewById(R.id.linlaHeaderProgress);

        progressBar.setVisibility(View.VISIBLE);
        database = FirebaseDatabase.getInstance().getReference();

        database.child("sinonim").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                daftarSinonim = new ArrayList<>();
                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    dataKamus dataSinonim = noteDataSnapshot.getValue(dataKamus.class);
                    dataSinonim.setKey(noteDataSnapshot.getKey());

                    daftarSinonim.add(dataSinonim);
                }
                adapter = new AdapterAntonimSinonim(daftarSinonim);
                rvView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println(databaseError.getDetails() + " " + databaseError.getMessage());
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.ketikdisini));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    filter(newText);
                    return false;
                }
            });
        }
        return true;
    }

    private void filter(String newText) {
        ArrayList<dataKamus> dataKamusList = new ArrayList<>();
        try {
            for (dataKamus s : daftarSinonim) {
                if (s.getJudul().toLowerCase().contains(newText.toLowerCase())) {
                    dataKamusList.add(s);
                }
            }
            adapter = new AdapterAntonimSinonim(dataKamusList);
            rvView.setAdapter(adapter);
        } catch (NullPointerException e) {
            Log.i("e", String.valueOf(e));
        }
    }
}
