package com.example.kasitom.chatroom;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kasitom.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MessageAdapter extends FirebaseListAdapter<ChatMessage> {
    private GlobalChat activity;
    private ChatMessage chatMessage;

    public MessageAdapter(GlobalChat activity, Class<ChatMessage> modelClass, int modelLayout, DatabaseReference ref) {
        super(activity, modelClass, modelLayout, ref);
        this.activity = activity;
    }

    @Override
    protected void populateView(View v, ChatMessage model, int position) {
        TextView messageText = v.findViewById(R.id.message_text);
        TextView messageUser = v.findViewById(R.id.message_user);
        //TextView messageTime = v.findViewById(R.id.message_time);

        if (chatMessage.getMessageUserId().equals(activity.getLoggedInUserName())){
            messageText.setText(model.getMessageText());
            messageUser.setText("Saya");
        }else {
            messageText.setText(model.getMessageText());
            messageUser.setText(model.getMessageUser());
        }


        // Format the date before showing it
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        chatMessage = getItem(position);
        if (chatMessage.getMessageUserId().equals(activity.getLoggedInUserName())) {
            view = activity.getLayoutInflater().inflate(R.layout.item_out_message, viewGroup, false);
        }
        else{
            view = activity.getLayoutInflater().inflate(R.layout.item_in_message, viewGroup, false);
        }
        //generating view
        populateView(view, chatMessage, position);

        return super.getView(position, view, viewGroup);
    }

    @Override
    public int getViewTypeCount() {
        // return the total number of view types. this value should never change
        // at runtime
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        // return a value between 0 and (getViewTypeCount - 1)
        return position % 2;
    }
}
