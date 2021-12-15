package com.in.doctor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.in.doctor.R;
import com.in.doctor.adapter.ProfileSettingAdapter;

public class ProfileSetting extends AppCompatActivity {


    ImageView nevBack, nevBackHeader;
    TextView headerTitle;

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setting);
        getSupportActionBar().hide();
        init();
    }

    public void init() {
        nevBack = findViewById(R.id.nevBack);
        nevBackHeader = findViewById(R.id.nevBackHeader);
        headerTitle = findViewById(R.id.header_title);


        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.pager);

        headerTitle.setText("Profile Setting");

        tabLayout.addTab(tabLayout.newTab().setText("Personal"));
        tabLayout.addTab(tabLayout.newTab().setText("Clinical"));
        tabLayout.addTab(tabLayout.newTab().setText("Lifestyle"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        ProfileSettingAdapter profileSettingAdapter = new ProfileSettingAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(profileSettingAdapter);
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
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

    }

}
