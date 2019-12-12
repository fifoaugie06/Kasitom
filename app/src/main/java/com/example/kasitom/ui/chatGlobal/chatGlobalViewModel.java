package com.example.kasitom.ui.chatGlobal;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class chatGlobalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public chatGlobalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}