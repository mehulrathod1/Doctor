package com.in.doctor.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.in.doctor.fragment.HomeCompleted;
import com.in.doctor.fragment.HomeUpcoming;
import com.in.doctor.fragment.ProfileSettingClinical;
import com.in.doctor.fragment.ProfileSettingLifestyle;
import com.in.doctor.fragment.ProfileSettingPersonal;

public class HomeTabAdapter extends FragmentPagerAdapter {

    Context context;
    int totalTabs;


    public HomeTabAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomeCompleted news = new HomeCompleted();
                return news;

            case 1:
                HomeUpcoming yourCity = new HomeUpcoming();
                return yourCity;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
