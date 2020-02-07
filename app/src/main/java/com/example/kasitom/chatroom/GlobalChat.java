package com.example.kasitom.chatroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.kasitom.R;
import com.example.kasitom.model.dataMessage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class GlobalChat extends AppCompatActivity {
    private EditText inp_msg;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_chat);

        ImageButton btn_SendMsg;

        getSupportActionBar().setTitle(R.string.chatglobal);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.list);
        btn_SendMsg = findViewById(R.id.btn_send);
        inp_msg = findViewById(R.id.inp_chat);

        btn_SendMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inp_msg.length() == 0) {
                    inp_msg.setError("Tidak Boleh Kosong");
                } else {
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .child("globalchat")
                            .push()
                            .setValue(new dataMessage(inp_msg.getText().toString(),
                                    FirebaseAuth.getInstance().getCurrentUser().getDisplayName(),
                                    FirebaseAuth.getInstance().getCurrentUser().getUid(),
                                    FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString(),
                                    new Date().getTime()
                            ));
                    inp_msg.setText("");
                }
            }
        });
        showAllOldMessages();
    }

    private void showAllOldMessages() {
        String loggedInUserName;
        MessageAdapter adapter;

        loggedInUserName = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("Main", "user id: " + loggedInUserName);

        adapter = new MessageAdapter(this, dataMessage.class, R.layout.item_in_message,
                FirebaseDatabase.getInstance().getReference().child("globalchat"));
        listView.setAdapter(adapter);
    }

    public String getLoggedInUserName() {
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
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
