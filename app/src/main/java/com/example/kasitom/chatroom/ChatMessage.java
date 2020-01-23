package com.example.kasitom.chatroom;

import android.net.Uri;

import java.net.URL;
import java.util.Date;

public class ChatMessage {
    private String messageText;
    private String messageUser;
    private String messageUserId;
    private String messageUserPhotoUri;
    private long messageUserTime;

    public  ChatMessage(){

    }

    public ChatMessage(String messageText, String messageUser, String messageUserId, String messageUserPhotoUri, long messageUserTime) {
         this.messageText = messageText;
         this.messageUser = messageUser;
         this.messageUserId = messageUserId;
         this.messageUserPhotoUri = messageUserPhotoUri;
         this.messageUserTime = messageUserTime;
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

    public String getMessageUserPhotoUri() {
        return messageUserPhotoUri;
    }

    public long getMessageUserTime() {
        return messageUserTime;
    }
}
