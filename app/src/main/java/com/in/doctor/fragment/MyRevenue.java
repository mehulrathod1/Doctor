package com.in.doctor.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.in.doctor.R;


public class MyRevenue extends Fragment {

    View view;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton Request;
    Fragment fragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_revenue, container, false);
        init();
        return view;
    }

    @SuppressLint("ResourceAsColor")
    public void init() {


        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        Request = view.findViewById(R.id.Request);
        bottomNavigationView.setBackgroundColor(android.R.color.transparent);
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);


        Request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = new ManageCalendar();

                loadFragment(fragment);

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        fragment = new HomeDashboard();
                        loadFragment(fragment);
                        break;
                    case R.id.Revenue:
                        fragment = new MyRevenue();
                        loadFragment(fragment);
                        break;
                    case R.id.Chats:
                        Toast.makeText(getContext(), "Chat", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Profile:
                        Toast.makeText(getContext(), "Profile", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.firstFrame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

}