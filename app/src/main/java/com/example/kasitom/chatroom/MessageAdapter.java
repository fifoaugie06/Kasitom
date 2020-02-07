package com.example.kasitom.chatroom;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kasitom.R;
import com.example.kasitom.model.dataMessage;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;

public class MessageAdapter extends FirebaseListAdapter<dataMessage> {
    private GlobalChat activity;
    private dataMessage dataMessage;

    MessageAdapter(GlobalChat activity, Class<dataMessage> modelClass, int modelLayout, DatabaseReference ref) {
        super(activity, modelClass, modelLayout, ref);
        this.activity = activity;
    }

    @Override
    protected void populateView(View v, dataMessage model, int position) {
        TextView messageText = v.findViewById(R.id.message_text);
        TextView messageUser = v.findViewById(R.id.message_user);
        //TextView messageTime = v.findViewById(R.id.message_time);

        if (dataMessage.getMessageUserId().equals(activity.getLoggedInUserName())) {
            messageText.setText(model.getMessageText());
            messageUser.setText(R.string.saya);
        } else {
            messageText.setText(model.getMessageText());
            messageUser.setText(model.getMessageUser());
        }
        // Format the date before showing it
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        dataMessage = getItem(position);
        if (dataMessage.getMessageUserId().equals(activity.getLoggedInUserName())) {
            view = activity.getLayoutInflater().inflate(R.layout.item_out_message, viewGroup, false);
        } else {
            view = activity.getLayoutInflater().inflate(R.layout.item_in_message, viewGroup, false);
        }
        //generating view
        populateView(view, dataMessage, position);

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
