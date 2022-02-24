package com.in.doctor.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.messaging.Constants;
import com.in.doctor.R;
import com.in.doctor.activity.AccountSetting;
import com.in.doctor.activity.CompletedAssignment;
import com.in.doctor.activity.DoctorProfile;
import com.in.doctor.activity.Home;
import com.in.doctor.activity.ManageCalendar;
import com.in.doctor.activity.MyQuestion;
import com.in.doctor.activity.MyReview;
import com.in.doctor.activity.MyWallet;
import com.in.doctor.activity.OnlineConsultants;
import com.in.doctor.activity.ProfileSetting;
import com.in.doctor.adapter.FindDoctorAdapter;
import com.in.doctor.adapter.HealthCareAdapter;
import com.in.doctor.adapter.HomeTabAdapter;
import com.in.doctor.adapter.MyQuestionAdapter;
import com.in.doctor.adapter.MyReviewAdapter;
import com.in.doctor.adapter.SliderPagerAdapter;
import com.in.doctor.global.Glob;
import com.in.doctor.model.CareAndCheckupModel;
import com.in.doctor.model.FindDoctorModel;
import com.in.doctor.model.MyQuestionModel;
import com.in.doctor.model.MyReviewModel;
import com.in.doctor.retrofit.Api;
import com.in.doctor.retrofit.RetrofitClient;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class HomeDashboard extends Fragment {

    View view;
    Fragment fragment;
    TextView viewAllDoctor, viewAllServices, viewAllCheckup;
    private ViewPager vp_slider;
    private LinearLayout ll_dots;
    SliderPagerAdapter sliderPagerAdapter;
    ArrayList<String> slider_image_list;
    private TextView[] dots;
    int page_position = 0;
    RecyclerView recyclerView, healthCheckupRecycler, healthCareRecycler;
    HealthCareAdapter healthCareAdapter;
    List<CareAndCheckupModel> careList = new ArrayList<>();
    List<CareAndCheckupModel> healthList = new ArrayList<>();
    FindDoctorAdapter adapter;
    List<FindDoctorModel> list = new ArrayList<>();
    LinearLayout doctorConsultant, completedAssignment, billingSegment, myQuestion, manageCalender, myReview, profileSetting, accountSetting;

    Spinner countryName;
    ArrayAdapter<String> countryNameAdapter;
    List<String> countryNameList = new ArrayList<>();

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");


    // For new Page

    Button viewAllReview, viewAllQuestion;
    TabLayout tabLayout;
    ViewPager viewPager;

    RecyclerView reviewRecycler, questionRecycler;
    MyReviewAdapter reviewAdapter;
    List<MyReviewModel.ReviewData> reviewList = new ArrayList<>();

    MyQuestionAdapter myQuestionAdapter;
    List<MyQuestionModel> myQuestionModelList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home_dashboard, container, false);

        init();
        recyclerData();
        healthCareData();
        healthCheckupData();
        addBottomDots(0);


        //for new page

        initData();
        clickEvent();
        getReview(Glob.Token, Glob.user_id);
        questionData();

        return view;
    }

    @SuppressLint("ResourceAsColor")
    public void init() {

        recyclerView = view.findViewById(R.id.findDoctor);
        healthCareRecycler = view.findViewById(R.id.healthCareRecycler);
        healthCheckupRecycler = view.findViewById(R.id.healthCheckupRecycler);
        doctorConsultant = view.findViewById(R.id.doctorConsultant);
        completedAssignment = view.findViewById(R.id.CompletedAssignment);
        viewAllDoctor = view.findViewById(R.id.viewAllDoctor);
        viewAllServices = view.findViewById(R.id.viewAllServices);
        viewAllCheckup = view.findViewById(R.id.viewAllCheckup);
        vp_slider = view.findViewById(R.id.vp_slider);
        ll_dots = view.findViewById(R.id.ll_dots);
        myReview = view.findViewById(R.id.myReview);
        myQuestion = view.findViewById(R.id.myQuestion);
        billingSegment = view.findViewById(R.id.billingSegment);
        countryName = view.findViewById(R.id.countryName);
        profileSetting = view.findViewById(R.id.profileSetting);
        manageCalender = view.findViewById(R.id.revenue);
        accountSetting = view.findViewById(R.id.accountSetting);


        countryNameList.add("Gujarat");
        countryNameList.add("Maharashtra");
        countryNameList.add("Rajasthan");


        countryNameAdapter = new ArrayAdapter<String>(getContext(), R.layout.profile_spinner_text, countryNameList);
        countryNameAdapter.setDropDownViewResource(R.layout.dropdown_item);
        countryName.setAdapter(countryNameAdapter);


        viewAllDoctor.setText(Html.fromHtml("<u>View All</u>"));
        viewAllServices.setText(Html.fromHtml("<u>View All</u>"));
        viewAllCheckup.setText(Html.fromHtml("<u>View All</u>"));

        slider_image_list = new ArrayList<>();
        slider_image_list.add("http://ciam.notionprojects.tech/assets/image/userprofile/1642061177_appointment-img.png");
        slider_image_list.add("http://ciam.notionprojects.tech/assets/image/userprofile/1642061177_appointment-img.png");
        slider_image_list.add("http://ciam.notionprojects.tech/assets/image/userprofile/1642061177_appointment-img.png");
        slider_image_list.add("http://ciam.notionprojects.tech/assets/image/userprofile/1642061177_appointment-img.png");
        sliderPagerAdapter = new SliderPagerAdapter(getActivity(), slider_image_list, new SliderPagerAdapter.Click() {
            @Override
            public void itemClick(int position) {
//                Intent intent = new Intent(getActivity(), DoctorProfile.class);
//                startActivity(intent);
//                sendNotification("e6_uV61OSsy-_z31aqlivI:APA91bF9AOxn6xrOFCgg0NzlV-Cul9lZcrEyzTZx1EbXlnka2QruIFx278GBIOq-A2j6T6q0uwy40mrWtvN4c-ghYLI_2ZrcW0quePAoMoarQZv78uWxZB3JsBiFCVV3x0IkbwB-53ZO");

            }
        });
        vp_slider.setAdapter(sliderPagerAdapter);
        vp_slider.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                addBottomDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final Handler handler = new Handler();

        final Runnable update = new Runnable() {
            public void run() {
                if (page_position == slider_image_list.size()) {
                    page_position = 0;
                } else {
                    page_position = page_position + 1;
                }
                vp_slider.setCurrentItem(page_position, true);
            }
        };

        new Timer().schedule(new TimerTask() {


            @Override
            public void run() {
                handler.post(update);
            }
        }, 100, 5000);


        doctorConsultant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), OnlineConsultants.class);
                startActivity(intent);
//                Fragment fragment = new DoctorConsultant();
//                loadFragment(fragment);
            }
        });
        completedAssignment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), CompletedAssignment.class);
                startActivity(intent);

            }
        });

        myReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), MyReview.class);
                startActivity(intent);

            }
        });

        myQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MyQuestion.class);
                startActivity(intent);

            }
        });


        billingSegment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyWallet.class);
                startActivity(intent);
            }
        });

        profileSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileSetting.class);
                startActivity(intent);
            }
        });
        accountSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AccountSetting.class);
                startActivity(intent);
            }
        });

        manageCalender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ManageCalendar.class);
                startActivity(intent);
            }
        });

        viewAllDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Fragment fragment = new DoctorConsultant();
//                loadFragment(fragment);

            }
        });
        viewAllServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fragment fragment = new CareServices();
//                loadFragment(fragment);
            }
        });
        viewAllCheckup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Fragment fragment = new CareServices();
//                loadFragment(fragment);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.firstFrame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void addBottomDots(int currentPage) {
        dots = new TextView[slider_image_list.size()];
        ll_dots.removeAllViews();
        if (getActivity() != null) {
            for (int i = 0; i < dots.length; i++) {
                dots[i] = new TextView(getActivity());
                dots[i].setText(Html.fromHtml("&#8226;"));
                dots[i].setTextSize(35);
                dots[i].setTextColor(Color.parseColor("#EFEFEF"));
                ll_dots.addView(dots[i]);
            }
            if (dots.length > 0)
                dots[currentPage].setTextColor(Color.parseColor("#233E8B"));
        }
    }

    public void recyclerData() {

        FindDoctorModel model = new FindDoctorModel("", "General Physician");
        FindDoctorModel model1 = new FindDoctorModel("", "Skin and Hair Specialist");
        FindDoctorModel model2 = new FindDoctorModel("", "Sexologist");
        FindDoctorModel model3 = new FindDoctorModel("", "Gynaecologist");
        FindDoctorModel model4 = new FindDoctorModel("", "Bone and joint Specialist");
        FindDoctorModel model5 = new FindDoctorModel("", "Ear Nose Throat");
        list.add(model);
        list.add(model1);
        list.add(model2);
        list.add(model3);
        list.add(model4);
        list.add(model5);

        adapter = new FindDoctorAdapter(list, getContext(), new FindDoctorAdapter.Click() {
            @Override
            public void onButtonClick(int position) {

//                Fragment fragment = new DoctorConsultant();
//                loadFragment(fragment);

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void healthCareData() {

        CareAndCheckupModel model = new CareAndCheckupModel("", "Covid Care");
        CareAndCheckupModel model1 = new CareAndCheckupModel("", "Physiotherapy");
        CareAndCheckupModel model2 = new CareAndCheckupModel("", "Medical Equipment");
        CareAndCheckupModel model3 = new CareAndCheckupModel("", "Gynaecologist");

        careList.add(model);
        careList.add(model1);
        careList.add(model2);
        careList.add(model3);


        healthCareAdapter = new HealthCareAdapter(careList, getContext(), new HealthCareAdapter.Click() {
            @Override
            public void onClick(int position) {

//                Fragment fragment = new CareServices();
//                loadFragment(fragment);
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        healthCareRecycler.setLayoutManager(mLayoutManager);
        healthCareRecycler.setAdapter(healthCareAdapter);
    }

    public void healthCheckupData() {

        CareAndCheckupModel model = new CareAndCheckupModel("", "Health Checkup 1");
        CareAndCheckupModel model1 = new CareAndCheckupModel("", "Health Checkup 2");
        CareAndCheckupModel model2 = new CareAndCheckupModel("", "Health Checkup 3");
        CareAndCheckupModel model3 = new CareAndCheckupModel("", "Health Checkup 4");
        healthList.add(model);
        healthList.add(model1);
        healthList.add(model2);
        healthList.add(model3);

        healthCareAdapter = new HealthCareAdapter(healthList, getContext(), new HealthCareAdapter.Click() {
            @Override
            public void onClick(int position) {

//                Fragment fragment = new CareServices();
//                loadFragment(fragment);

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        healthCheckupRecycler.setLayoutManager(mLayoutManager);
        healthCheckupRecycler.setAdapter(healthCareAdapter);

    }

    private void sendNotification(final String regToken) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                try {
                    OkHttpClient client = new OkHttpClient();
                    JSONObject json = new JSONObject();
                    JSONObject dataJson = new JSONObject();
                    dataJson.put("body", "");
                    dataJson.put("title", "dummy notification");
                    json.put("notification", dataJson);
                    json.put("to", regToken);
                    RequestBody body = RequestBody.create(JSON, json.toString());
                    Request request = new Request.Builder()
                            .header("Authorization", "key=" + "AAAAEhxA8sc:APA91bGzKFx7gAT8wnp2rCjvhz12SZ-nGhg6HF3dffOhfOBpKAxYWvRpfkoRmWSnZd2_W1-ez8gizm1di1BAjmA-HBvD5QnVoPTEPwNTmGBR1NSONAcLV36OOZ_hlhMYMBDqVCEesOOQ")
                            .url("https://fcm.googleapis.com/fcm/send")
                            .post(body)
                            .build();
                    Response response = client.newCall(request).execute();
                    String finalResponse = response.body().string();
                    Log.e("doInBackground", "doInBackground: " + finalResponse);
                } catch (Exception e) {
                    //Log.d(TAG,e+"");
                }
                return null;
            }
        }.execute();

    }


    // for new Page

    public void initData() {

        Glob.progressDialog(getContext());
        tabLayout = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.pager);
        reviewRecycler = view.findViewById(R.id.reviewRecycler);
        questionRecycler = view.findViewById(R.id.questionRecycler);
        viewAllReview = view.findViewById(R.id.viewAllReview);
        viewAllQuestion = view.findViewById(R.id.viewAllQuestion);

        tabLayout.addTab(tabLayout.newTab().setText("Completed"));
        tabLayout.addTab(tabLayout.newTab().setText("Upcoming"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        HomeTabAdapter homeTabAdapter = new HomeTabAdapter(getChildFragmentManager(), getContext(), tabLayout.getTabCount());
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

    public void clickEvent() {

        viewAllQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyQuestion.class);
                startActivity(intent);
            }
        });
        viewAllReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyReview.class);
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


        reviewAdapter = new MyReviewAdapter(reviewList, getContext(), new MyReviewAdapter.Click() {
            @Override
            public void onItemClick(int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
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
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        questionRecycler.setLayoutManager(mLayoutManager);
        questionRecycler.setAdapter(myQuestionAdapter);
        questionRecycler.setNestedScrollingEnabled(false);
        questionRecycler.setHasFixedSize(true);

    }

}

