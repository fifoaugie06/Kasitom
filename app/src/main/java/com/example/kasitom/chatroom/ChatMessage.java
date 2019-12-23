package com.example.kasitom.chatroom;

import android.net.Uri;

import java.net.URL;
import java.util.Date;

public class ChatMessage {
    private String messageText;
    private String messageUser;
    private String messageUserId;

    public  ChatMessage(){

    }

    public ChatMessage(String messageText, String messageUser, String messageUserId) {
         this.messageText = messageText;
         this.messageUser = messageUser;
         this.messageUserId = messageUserId;
    }


    public String getMessageUserId() {
        return messageUserId;
    }

    public String getMessageText() {
        return messageText;
    }

    public String getMessageUser() {
        return messageUser;
    }

}
