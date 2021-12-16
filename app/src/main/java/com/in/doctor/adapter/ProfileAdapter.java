package com.in.doctor.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.in.doctor.fragment.ProfileClinical;
import com.in.doctor.fragment.ProfileLifestyle;
import com.in.doctor.fragment.ProfilePersonal;

public class ProfileAdapter extends FragmentPagerAdapter {

    Context context;
    int totalTabs;

    public ProfileAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ProfilePersonal news = new ProfilePersonal();
                return news;

            case 1:
                ProfileClinical yourCity = new ProfileClinical();
                return yourCity;

            case 2:

                ProfileLifestyle gujrat = new ProfileLifestyle();
                return gujrat;


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
