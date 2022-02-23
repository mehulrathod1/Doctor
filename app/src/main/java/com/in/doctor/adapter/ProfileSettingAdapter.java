package com.in.doctor.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.in.doctor.fragment.ProfileSettingClinical;
import com.in.doctor.fragment.ProfileSettingLifestyle;
import com.in.doctor.fragment.ProfileSettingPersonal;

public class ProfileSettingAdapter extends FragmentPagerAdapter {
    Context context;
    int totalTabs;

    public ProfileSettingAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    ProfileSettingPersonal news = new ProfileSettingPersonal();
                    return news;

                case 1:
                    ProfileSettingClinical yourCity = new ProfileSettingClinical();
                    return yourCity;

                case 2:

                    ProfileSettingLifestyle gujrat = new ProfileSettingLifestyle();
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
