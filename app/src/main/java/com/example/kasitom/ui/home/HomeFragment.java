package com.example.kasitom.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


import com.example.kasitom.R;
import com.example.kasitom.antonimSinonim.AntonimActivity;
import com.example.kasitom.antonimSinonim.SinonimActivity;
import com.example.kasitom.chatroom.GlobalChat;
import com.example.kasitom.quiz.QuizActivity;

public class HomeFragment extends Fragment {
    private View view;
    private CardView cv_btnAntonim, cv_btnSinonim, cv_btnGlobalChat, cv_btnQuiz;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        cv_btnAntonim = view.findViewById(R.id.btn_antonim);
        cv_btnSinonim = view.findViewById(R.id.btn_sinonim);
        cv_btnGlobalChat = view.findViewById(R.id.btn_chatglobal);
        cv_btnQuiz = view.findViewById(R.id.btn_quiz);

        cv_btnAntonim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AntonimActivity.class);
                startActivity(intent);
            }
        });
        cv_btnSinonim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SinonimActivity.class);
                startActivity(intent);
            }
        });
        cv_btnGlobalChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), GlobalChat.class);
                startActivity(intent);
            }
        });
        cv_btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), QuizActivity.class);
                startActivity(intent);
            }
        });
    }
}