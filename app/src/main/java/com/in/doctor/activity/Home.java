package com.in.doctor.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.in.doctor.R;
import com.in.doctor.fragment.Chat;
import com.in.doctor.fragment.DoctorConsultSecond;
import com.in.doctor.fragment.DoctorConsultant;
import com.in.doctor.fragment.HomeDashboard;
import com.in.doctor.fragment.MyRevenue;
import com.in.doctor.fragment.Profile;
import com.in.doctor.global.Glob;
import com.in.doctor.model.GetFcmTokenModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        getFcmToken(Glob.Token, "13");
    }

    @SuppressLint("ResourceAsColor")
    public void init() {
        coordinator = findViewById(R.id.coordinator);
        Navigation = findViewById(R.id.Navigation);
        firstFrame = findViewById(R.id.firstFrame);
        header_title = findViewById(R.id.header_title);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        request = findViewById(R.id.Request);

        bottomNavigationView.setBackgroundColor(android.R.color.white);
        bottomNavigationView.getMenu().findItem(R.id.home).setChecked(true);
        bottomNavigationView.getMenu().getItem(2).setEnabled(false);

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
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.firstFrame, new MyRevenue());
                        transaction.commit();
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

                        moveNext(ProfileSetting.class);
                        drawerLayout.close();
                        break;
                    case R.id.ManageCalender:

                        moveNext(ManageCalendar.class);
                        drawerLayout.close();
                        break;

                    case R.id.BookingRequest:

                        moveNext(Request.class);
                        drawerLayout.close();
                        break;

                    case R.id.BookedAppointment:
                        moveNext(BookedAppointment.class);
                        drawerLayout.close();
                        break;

                    case R.id.OnlineConsultant:
                        moveNext(OnlineConsultants.class);
                        drawerLayout.close();
                        break;

                    case R.id.CompletedAssignment:

                        moveNext(CompletedAssignment.class);
                        drawerLayout.close();
                        break;

                    case R.id.MyReview:

                        moveNext(MyReview.class);
                        drawerLayout.close();
                        break;
                    case R.id.MYQuestion:
                        moveNext(MyQuestion.class);
                        drawerLayout.close();
                        break;
                    case R.id.BillingSegment:
                        moveNext(MyWallet.class);
                        drawerLayout.close();
                        break;
                    case R.id.AccountSetting:

                        moveNext(AccountSetting.class);
                        drawerLayout.close();
                        break;
                    case R.id.Logout:
//                        SharedPreferences.Editor editor = getSharedPreferences("MyPref", MODE_PRIVATE).edit();
//                        editor.remove("token");
//                        editor.commit();
                        moveNext(SignIn.class);
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
//        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void moveNext(Class activity) {
        Intent intent = new Intent(getApplicationContext(), activity);
        startActivity(intent);

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


    public void getFcmToken(String token, String doctor_id) {
        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);

        call.getFcmToken(token, doctor_id).enqueue(new Callback<GetFcmTokenModel>() {
            @Override
            public void onResponse(Call<GetFcmTokenModel> call, Response<GetFcmTokenModel> response) {
                GetFcmTokenModel model = response.body();

                Log.e("main", "onResponse: " + model.getData().getFcm_token());
            }

            @Override
            public void onFailure(Call<GetFcmTokenModel> call, Throwable t) {

            }
        });

    }
}
