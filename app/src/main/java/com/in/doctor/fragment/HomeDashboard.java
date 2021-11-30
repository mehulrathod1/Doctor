package com.in.doctor.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.in.doctor.R;
import com.in.doctor.activity.Home;
import com.in.doctor.adapter.BookedAppointmentAdapter;
import com.in.doctor.adapter.FindDoctorAdapter;
import com.in.doctor.adapter.SliderPagerAdapter;
import com.in.doctor.model.BookedAppointmentModel;
import com.in.doctor.model.FindDoctorModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeDashboard extends Fragment {


    View view;
    Fragment fragment;

    private ViewPager vp_slider;
    private LinearLayout ll_dots;
    SliderPagerAdapter sliderPagerAdapter;
    ArrayList<String> slider_image_list;
    private TextView[] dots;
    int page_position = 0;

    RecyclerView recyclerView;
    FindDoctorAdapter adapter;
    List<FindDoctorModel> list = new ArrayList<>();


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

        return view;
    }

    @SuppressLint("ResourceAsColor")
    public void init() {

        recyclerView = view.findViewById(R.id.findDoctor);


        vp_slider = view.findViewById(R.id.vp_slider);
        ll_dots = view.findViewById(R.id.ll_dots);

        slider_image_list = new ArrayList<>();

        addBottomDots(0);

        slider_image_list.add("https://wallpaperaccess.com/full/297372.jpg");
        slider_image_list.add("https://www.teahub.io/photos/full/68-683520_beautiful-girl-wallpapers-hd.jpg");
        slider_image_list.add("https://wallpaperaccess.com/full/1198406.jpg");
        slider_image_list.add("https://www.wallpaperuse.com/wallp/50-509102_m.jpg");
        sliderPagerAdapter = new SliderPagerAdapter(getActivity(), slider_image_list);
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

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}
