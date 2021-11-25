package com.in.doctor.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.in.doctor.R;
import com.in.doctor.fragment.BookedAppointment;
import com.in.doctor.fragment.CompletedAssignment;
import com.in.doctor.fragment.ManageBooking;
import com.in.doctor.fragment.ManageCalendar;
import com.in.doctor.fragment.MyReview;
import com.in.doctor.fragment.OnlineConsultants;
import com.in.doctor.fragment.ProfileSetting;

public class Home extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView Navigation;
    ImageView nevBack, nevBackHeader;
    View toolbar;

    FrameLayout firstFrame;

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Navigation = findViewById(R.id.Navigation);
        firstFrame = findViewById(R.id.firstFrame);
        toolbar = findViewById(R.id.toolbar);

        getSupportActionBar().hide();

        fragment = new ProfileSetting();

        View headerLayout = Navigation.inflateHeaderView(R.layout.nev_header);
        nevBackHeader = headerLayout.findViewById(R.id.nevBackHeader);

        nevBack = findViewById(R.id.nevBack);

        drawerLayout = findViewById(R.id.my_drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.open();
            }
        });
        nevBackHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.close();
            }
        });

        Navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.ProfileSetting:
                        toolbar.setVisibility(View.GONE);
                        drawerLayout.close();
                        fragment = new ProfileSetting();
                        loadFragment(fragment);
                        break;
                    case R.id.ManageCalender:
                        toolbar.setVisibility(View.GONE);
                        fragment = new ManageCalendar();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;

                    case R.id.BookingRequest:
                        toolbar.setVisibility(View.GONE);
                        fragment = new ManageBooking();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;

                    case R.id.BookedAppointment:
                        toolbar.setVisibility(View.GONE);
                        fragment = new BookedAppointment();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;

                    case R.id.OnlineConsultant:
                        toolbar.setVisibility(View.GONE);
                        fragment = new OnlineConsultants();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;


                    case R.id.CompletedAssignment:
                        toolbar.setVisibility(View.GONE);
                        fragment = new CompletedAssignment();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;

                    case R.id.MyReview:
                        toolbar.setVisibility(View.GONE);
                        fragment = new MyReview();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;
                    case R.id.MYQuestion:
                        drawerLayout.close();
                        break;
                    case R.id.BillingSegment:
                        drawerLayout.close();
                        break;
                    case R.id.AccountSetting:
                        drawerLayout.close();
                        break;
                    case R.id.Logout:
                        drawerLayout.close();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//frame_container is your layout name in xml file
        transaction.replace(R.id.firstFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
