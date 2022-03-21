package com.in.doctor.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.in.doctor.R;
import com.in.doctor.adapter.TransactionHistoryAdapter;
import com.in.doctor.fragment.Chat;
import com.in.doctor.fragment.HomeDashboard;
import com.in.doctor.fragment.Profile;
import com.in.doctor.model.TransactionHistoryModel;

import java.util.ArrayList;
import java.util.List;

public class MyRevenueActivity extends AppCompatActivity {


    public DrawerLayout drawerLayout;
    public ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView Navigation;
    ImageView nevBack, nevBackHeader;
    //    FrameLayout firstFrame;
    View header;
    TextView header_title;
    Fragment fragment;
    DrawerLayout my_drawer_layout;
    CoordinatorLayout coordinator;
    BottomNavigationView bottomNavigationView;
    FloatingActionButton request;

    TransactionHistoryAdapter adapter;
    RecyclerView recyclerView;
    List<TransactionHistoryModel> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_revenue);
        getSupportActionBar().hide();

        init();
        recyclerData();
    }

    @SuppressLint("ResourceAsColor")
    public void init() {
        recyclerView = findViewById(R.id.recycler);


        coordinator = findViewById(R.id.coordinator);
        Navigation = findViewById(R.id.Navigation);
//        firstFrame = findViewById(R.id.firstFrame);
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

                Intent intent = new Intent(getApplicationContext(), ManageCalendar.class);
                startActivity(intent);

            }
        });

        header_title.setText("My Revenue");
        bottomNavigationView.setSelectedItemId(R.id.Revenue);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;

                switch (item.getItemId()) {

                    case R.id.home:

                        intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);

                        break;
                    case R.id.Revenue:
                        intent = new Intent(getApplicationContext(), MyRevenueActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.Chats:
                        intent = new Intent(getApplicationContext(), MyChatActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);
                        break;

                    case R.id.Profile:
                        intent = new Intent(getApplicationContext(), MyProfileActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);
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

    public void moveNext(Class activity) {
        Intent intent = new Intent(getApplicationContext(), activity);
        startActivity(intent);
    }


    public void recyclerData() {

        TransactionHistoryModel model = new TransactionHistoryModel("9956328", "27/09/2021", "9956328", "$199");
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);


        adapter = new TransactionHistoryAdapter(list, getApplicationContext());

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);

        super.onBackPressed();

    }
}