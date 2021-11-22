package com.in.doctor.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.in.doctor.R;
import com.in.doctor.activity.Home;
import com.in.doctor.adapter.ProfileSettingAdapter;

public class ProfileSetting extends Fragment {
    ImageView nevBack, nevBackHeader;
    TextView headerTitle;
    View view;

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_profile_setting, container, false);

        init();
        return view;
    }

    public void init() {
        nevBack = view.findViewById(R.id.nevBack);
        nevBackHeader = view.findViewById(R.id.nevBackHeader);
        headerTitle = view.findViewById(R.id.header_title);


        tabLayout = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.pager);

        headerTitle.setText("Profile Setting");
        headerTitle.setTextColor(Color.parseColor("#3cb98f"));

        tabLayout.addTab(tabLayout.newTab().setText("Personal"));
        tabLayout.addTab(tabLayout.newTab().setText("Clinical"));
        tabLayout.addTab(tabLayout.newTab().setText("Lifestyle"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        ProfileSettingAdapter profileSettingAdapter =new ProfileSettingAdapter(getChildFragmentManager(),getActivity(),tabLayout.getTabCount());viewPager.setAdapter(profileSettingAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });


        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Home.class);
                startActivity(intent);
            }
        });

    }
}