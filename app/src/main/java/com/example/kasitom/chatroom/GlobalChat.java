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
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class GlobalChat extends AppCompatActivity {
    private EditText inp_msg;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_chat);

        final GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(GlobalChat.this);

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
                    if (account != null) {
                        FirebaseDatabase.getInstance()
                                .getReference()
                                .child("globalchat")
                                .push()
                                .setValue(new dataMessage(inp_msg.getText().toString(),
                                        account.getDisplayName(),
                                        account.getId(),
                                        account.getPhotoUrl().toString(),
                                        new Date().getTime()
                                ));
                        inp_msg.setText("");
                    }
                }
            }
        });
        showAllOldMessages();
    }

    private void showAllOldMessages() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(GlobalChat.this);
        String loggedInUserName;
        MessageAdapter adapter;

        loggedInUserName = account.getId();
        Log.d("Main", "user id: " + loggedInUserName);

        adapter = new MessageAdapter(this, dataMessage.class, R.layout.item_in_message,
                FirebaseDatabase.getInstance().getReference().child("globalchat"));
        listView.setAdapter(adapter);
    }

    public String getLoggedInUserName() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(GlobalChat.this);
        return account.getId();
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
