package com.in.doctor.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.in.doctor.R;
import com.in.doctor.activity.Home;

public class AccountSetting extends Fragment {

    ImageView nevBack;
    TextView headerTitle;
    View view;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_account_setting, container, false);
        init();
        return view;

    }

    public void init() {
        nevBack = view.findViewById(R.id.nevBack);
        headerTitle = view.findViewById(R.id.header_title);

        headerTitle.setText("Account Setting");

        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });

    }

}