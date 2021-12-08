package com.in.doctor.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.in.doctor.R;
import com.in.doctor.adapter.SliderPagerAdapter;
import com.in.doctor.fragment.AccountSetting;
import com.in.doctor.fragment.BookedAppointment;
import com.in.doctor.fragment.Chat;
import com.in.doctor.fragment.CompletedAssignment;
import com.in.doctor.fragment.DoctorConsultSecond;
import com.in.doctor.fragment.DoctorConsultant;
import com.in.doctor.fragment.HomeDashboard;
import com.in.doctor.fragment.ManageBooking;
import com.in.doctor.fragment.ManageCalendar;
import com.in.doctor.fragment.MyQuestion;
import com.in.doctor.fragment.MyRevenue;
import com.in.doctor.fragment.MyReview;
import com.in.doctor.fragment.MyWallet;
import com.in.doctor.fragment.OnlineConsultants;
import com.in.doctor.fragment.Profile;
import com.in.doctor.fragment.ProfileSetting;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity {

    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView Navigation;
    ImageView nevBack, nevBackHeader;
    FrameLayout firstFrame;
    View header;
    TextView header_title;
    Fragment fragment;
    DrawerLayout my_drawer_layout;
    CoordinatorLayout coordinator;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton request;


    RelativeLayout rl_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fragment = new HomeDashboard();
        loadFragment(fragment);

        int index = getIntent().getIntExtra("s", 0);
        if (index == 1) {
            fragment = new DoctorConsultant();
            loadFragment(fragment);
        }
        if (index == 2) {

            fragment = new DoctorConsultSecond();
            loadFragment(fragment);

        }

        init();
    }

    @SuppressLint("ResourceAsColor")
    public void init() {
        coordinator = findViewById(R.id.coordinator);
        Navigation = findViewById(R.id.Navigation);
        firstFrame = findViewById(R.id.firstFrame);
        header_title = findViewById(R.id.header_title);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        request = findViewById(R.id.Request);
        bottomNavigationView.setBackgroundColor(android.R.color.transparent);
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);

        coordinator.setVisibility(View.VISIBLE);

        my_drawer_layout = findViewById(R.id.my_drawer_layout);

        header = findViewById(R.id.header);

        getSupportActionBar().hide();


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

        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), Request.class);
                startActivity(intent);

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:

                        header_title.setText("DCP");
                        fragment = new HomeDashboard();
                        loadFragment(fragment);
                        break;
                    case R.id.Revenue:
                        header_title.setText("My Revenue");
                        fragment = new MyRevenue();
                        loadFragment(fragment);
                        break;
                    case R.id.Chats:
                        header_title.setText("Profile");
                        fragment = new Chat();
                        loadFragment(fragment);
                        break;
                    case R.id.Profile:
                        header_title.setText("Profile");
                        fragment = new Profile();
                        loadFragment(fragment);
                        Toast.makeText(getApplicationContext(), "Profile", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });


        Navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.ProfileSetting:

                        coordinator.setVisibility(View.GONE);
                        my_drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        header.setVisibility(View.GONE);
                        fragment = new ProfileSetting();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;
                    case R.id.ManageCalender:
                        coordinator.setVisibility(View.GONE);
                        my_drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        header.setVisibility(View.GONE);
                        fragment = new ManageCalendar();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;

                    case R.id.BookingRequest:
                        coordinator.setVisibility(View.GONE);
                        my_drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        header.setVisibility(View.GONE);
                        fragment = new ManageBooking();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;

                    case R.id.BookedAppointment:
                        coordinator.setVisibility(View.GONE);
                        my_drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        header.setVisibility(View.GONE);
                        fragment = new BookedAppointment();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;

                    case R.id.OnlineConsultant:
                        coordinator.setVisibility(View.GONE);
                        my_drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        header.setVisibility(View.GONE);
                        fragment = new OnlineConsultants();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;

                    case R.id.CompletedAssignment:
                        coordinator.setVisibility(View.GONE);
                        my_drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        header.setVisibility(View.GONE);
                        fragment = new CompletedAssignment();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;

                    case R.id.MyReview:
                        coordinator.setVisibility(View.GONE);
                        my_drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        header.setVisibility(View.GONE);
                        fragment = new MyReview();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;
                    case R.id.MYQuestion:
                        coordinator.setVisibility(View.GONE);
                        my_drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        header.setVisibility(View.GONE);
                        fragment = new MyQuestion();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;
                    case R.id.BillingSegment:
                        coordinator.setVisibility(View.GONE);
                        my_drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        header.setVisibility(View.GONE);
                        fragment = new MyWallet();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;
                    case R.id.AccountSetting:
                        coordinator.setVisibility(View.GONE);
                        my_drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
                        header.setVisibility(View.GONE);
                        fragment = new AccountSetting();
                        loadFragment(fragment);
                        drawerLayout.close();
                        break;
                    case R.id.Logout:
//                        coordinator.setVisibility(View.GONE);
//                        my_drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
//                        header.setVisibility(View.GONE);
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

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.firstFrame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (coordinator.getVisibility() == View.GONE) {
            coordinator.setVisibility(View.VISIBLE);
            header.setVisibility(View.VISIBLE);

        } else {
//            moveTaskToBack(true);
//            finish();
        }
//        Intent intent = new Intent(getApplicationContext(), Home.class);


    }
}
