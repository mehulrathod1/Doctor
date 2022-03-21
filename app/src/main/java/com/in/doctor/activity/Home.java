package com.in.doctor.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.in.doctor.R;
import com.in.doctor.adapter.HomeTabAdapter;
import com.in.doctor.adapter.MyQuestionAdapter;
import com.in.doctor.adapter.MyReviewAdapter;
import com.in.doctor.fragment.Chat;
import com.in.doctor.fragment.DoctorConsultSecond;
import com.in.doctor.fragment.DoctorConsultant;
import com.in.doctor.fragment.HomeDashboard;
import com.in.doctor.fragment.MyRevenue;
import com.in.doctor.fragment.Profile;
import com.in.doctor.global.Glob;
import com.in.doctor.model.GetFcmTokenModel;
import com.in.doctor.model.MyQuestionModel;
import com.in.doctor.model.MyReviewModel;
import com.in.doctor.model.RevenueModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

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

    RelativeLayout rl_main;

    Button viewAllReview, viewAllQuestion;
    TabLayout tabLayout;
    ViewPager viewPager;
    TextView completedRevenue;

    RecyclerView reviewRecycler, questionRecycler;
    MyReviewAdapter reviewAdapter;
    List<MyReviewModel.ReviewData> reviewList = new ArrayList<>();

    MyQuestionAdapter myQuestionAdapter;
    List<MyQuestionModel> myQuestionModelList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        fragment = new HomeDashboard();
//        loadFragment(fragment);


        init();
        getFcmToken(Glob.Token, Glob.user_id);
        initData();
        clickEvent();
        getReview(Glob.Token, Glob.user_id);
        questionData();
        getRevenue(Glob.Token, Glob.user_id);
    }

    @SuppressLint("ResourceAsColor")
    public void init() {
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

        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;

                switch (item.getItemId()) {
                    case R.id.home:

                        header_title.setText("DCP");
                        intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.Revenue:
                        header_title.setText("My Revenue");
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

//    private void loadFragment(Fragment fragment) {
//
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.firstFrame, fragment);
//        transaction.commit();
//    }

    public void moveNext(Class activity) {
        Intent intent = new Intent(getApplicationContext(), activity);
        startActivity(intent);
    }

    public void getFcmToken(String token, String doctor_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);

        call.getFcmToken(token, doctor_id).enqueue(new Callback<GetFcmTokenModel>() {
            @Override
            public void onResponse(Call<GetFcmTokenModel> call, Response<GetFcmTokenModel> response) {

                GetFcmTokenModel model = response.body();
                Log.e("FCMtoken", "onResponse: " + model.getData().getFcm_token());

            }

            @Override
            public void onFailure(Call<GetFcmTokenModel> call, Throwable t) {

            }
        });

    }


    public void initData() {

        Glob.progressDialog(this);
        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.pager);
        reviewRecycler = findViewById(R.id.reviewRecycler);
        questionRecycler = findViewById(R.id.questionRecycler);
        viewAllReview = findViewById(R.id.viewAllReview);
        viewAllQuestion = findViewById(R.id.viewAllQuestion);
        completedRevenue = findViewById(R.id.completedRevenue);

        tabLayout.addTab(tabLayout.newTab().setText("Completed"));
        tabLayout.addTab(tabLayout.newTab().setText("Upcoming"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        HomeTabAdapter homeTabAdapter = new HomeTabAdapter(getSupportFragmentManager(), getApplicationContext(), tabLayout.getTabCount());
        viewPager.setAdapter(homeTabAdapter);
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

    }

    public void getRevenue(String token, String doctor_id) {

        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.getRevenue(token, doctor_id).enqueue(new Callback<RevenueModel>() {
            @Override
            public void onResponse(Call<RevenueModel> call, retrofit2.Response<RevenueModel> response) {

                RevenueModel revenueModel = response.body();


                RevenueModel.Revenue revenue = revenueModel.getData();

                completedRevenue.setText("₹ " + revenue.getRevenue());


            }

            @Override
            public void onFailure(Call<RevenueModel> call, Throwable t) {

            }
        });
    }

    public void clickEvent() {

        viewAllQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyQuestion.class);
                startActivity(intent);
            }
        });
        viewAllReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MyReview.class);
                startActivity(intent);
            }
        });
    }


    public void getReview(String token, String doctor_id) {
        Api call = RetrofitClient.getClient(Glob.Base_Url).create(Api.class);
        Glob.dialog.show();

        call.getReview(token, doctor_id).enqueue(new Callback<MyReviewModel>() {
            @Override
            public void onResponse(Call<MyReviewModel> call, retrofit2.Response<MyReviewModel> response) {

                MyReviewModel myReviewModel = response.body();

                List<MyReviewModel.ReviewData> dataList = myReviewModel.getReviewDataList();

                if (dataList.size() != 0) {
                    for (int i = 0; i < dataList.size(); i++) {

                        MyReviewModel.ReviewData model = dataList.get(i);

                        MyReviewModel.ReviewData data = new MyReviewModel.ReviewData(model.getReview_id(), model.getPatient_id(),
                                model.getUserName(), model.getReview(), model.getRating(), model.getDate(), model.getProfile_image());


                        reviewList.add(data);
                        Collections.sort(reviewList, new Comparator<MyReviewModel.ReviewData>() {
                            @Override
                            public int compare(MyReviewModel.ReviewData o1, MyReviewModel.ReviewData o2) {
                                return o2.getRating().compareTo(o1.getRating());
                            }
                        });

                        Glob.dialog.dismiss();
                    }
                    reviewData();
                }
            }

            @Override
            public void onFailure(Call<MyReviewModel> call, Throwable t) {

            }
        });
    }

    public void reviewData() {

        reviewAdapter = new MyReviewAdapter(reviewList, getApplicationContext(), new MyReviewAdapter.Click() {
            @Override
            public void onItemClick(int position) {

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        reviewRecycler.setLayoutManager(mLayoutManager);
        reviewAdapter.notifyDataSetChanged();
        reviewRecycler.setNestedScrollingEnabled(false);
        reviewRecycler.setAdapter(reviewAdapter);
    }

    public void questionData() {

        MyQuestionModel model = new MyQuestionModel("Lorem ipsum dolor sit amet, consetetur. ?", "27/09/2021", "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea. ", "Submit Your Answer");
        myQuestionModelList.add(model);
        myQuestionModelList.add(model);
        myQuestionModelList.add(model);
        myQuestionModelList.add(model);
        myQuestionModelList.add(model);


        myQuestionAdapter = new MyQuestionAdapter(myQuestionModelList);
        ((SimpleItemAnimator) questionRecycler.getItemAnimator()).setSupportsChangeAnimations(false);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        questionRecycler.setLayoutManager(mLayoutManager);
        questionRecycler.setAdapter(myQuestionAdapter);
        questionRecycler.setNestedScrollingEnabled(false);
        questionRecycler.setHasFixedSize(true);

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
