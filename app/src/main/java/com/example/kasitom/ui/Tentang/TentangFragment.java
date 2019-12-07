package com.example.kasitom.ui.Tentang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.kasitom.R;

public class TentangFragment extends Fragment {

    private TentangViewModel tentangViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        tentangViewModel =
                ViewModelProviders.of(this).get(TentangViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tentang, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        tentangViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}