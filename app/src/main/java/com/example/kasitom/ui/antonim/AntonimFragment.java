package com.example.kasitom.ui.antonim;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kasitom.MainActivity;
import com.example.kasitom.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AntonimFragment extends Fragment {


    public AntonimFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_antonim, container, false);

        return view;
    }

}
