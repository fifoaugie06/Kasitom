package com.example.kasitom.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.kasitom.R;
import com.example.kasitom.ui.Tentang.TentangFragment;
import com.example.kasitom.ui.antonim.AntonimFragment;
import com.example.kasitom.ui.sinonim.SinonimFragment;
import com.google.android.material.navigation.NavigationView;

public class HomeFragment extends Fragment {
    private View view;
    private CardView cv_btnAntonim, cv_btnSinonim;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initView();

        cv_btnAntonim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AntonimFragment antonimFragment = new AntonimFragment();

                getFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, antonimFragment)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

                Toast.makeText(getContext(), "Antonim", Toast.LENGTH_LONG).show();
            }
        });
        cv_btnSinonim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SinonimFragment sinonimFragment = new SinonimFragment();

                getFragmentManager().beginTransaction()
                        .replace(R.id.nav_host_fragment, sinonimFragment)
                        .addToBackStack(null)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit();

                Toast.makeText(getContext(), "Sinonim", Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    private void initView() {
        cv_btnAntonim = view.findViewById(R.id.btn_antonim);
        cv_btnSinonim = view.findViewById(R.id.btn_sinonim);
    }
}