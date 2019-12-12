package com.example.kasitom.ui.chatGlobal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kasitom.R;

public class chatGlobalFragment extends Fragment {

    private chatGlobalViewModel chatGlobalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        chatGlobalViewModel =
                ViewModelProviders.of(this).get(chatGlobalViewModel.class);
        View root = inflater.inflate(R.layout.fragment_chatglobal, container, false);
        final TextView textView = root.findViewById(R.id.text_send);
        chatGlobalViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}