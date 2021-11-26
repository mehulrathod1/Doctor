package com.in.doctor.fragment;

import android.content.Intent;
import android.os.Bundle;

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
import com.in.doctor.adapter.MyWalletAdapter;
import com.in.doctor.adapter.ProfileSettingAdapter;

public class MyWallet extends Fragment {


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
        view = inflater.inflate(R.layout.fragment_my_wallet, container, false);

        init();
        return view;
    }


    public void init() {
        nevBack = view.findViewById(R.id.nevBack);
        nevBackHeader = view.findViewById(R.id.nevBackHeader);
        headerTitle = view.findViewById(R.id.header_title);


        tabLayout = view.findViewById(R.id.tabb);
        viewPager = view.findViewById(R.id.pager);

        headerTitle.setText("My Wallet");

        nevBack.setVisibility(View.GONE);

        tabLayout.addTab(tabLayout.newTab().setText("Wallet Transaction History"));
        tabLayout.addTab(tabLayout.newTab().setText("Request for Redemption"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


//        ProfileSettingAdapter profileSettingAdapter =new ProfileSettingAdapter(getChildFragmentManager(),getActivity(),tabLayout.getTabCount());
//        viewPager.setAdapter(profileSettingAdapter);
        MyWalletAdapter myWalletAdapter = new MyWalletAdapter(getChildFragmentManager(), getContext(), tabLayout.getTabCount());
        viewPager.setAdapter(myWalletAdapter);
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