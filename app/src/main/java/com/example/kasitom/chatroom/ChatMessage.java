package com.example.kasitom.chatroom;

import android.net.Uri;

import java.net.URL;
import java.util.Date;

public class ChatMessage {
    private String messageText;
    private String messageUser;
    private String messageUserId;
    private String messageUserPhotoUri;

    public  ChatMessage(){

    }

    public ChatMessage(String messageText, String messageUser, String messageUserId, String messageUserPhotoUri) {
         this.messageText = messageText;
         this.messageUser = messageUser;
         this.messageUserId = messageUserId;
         this.messageUserPhotoUri = messageUserPhotoUri;
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
}
