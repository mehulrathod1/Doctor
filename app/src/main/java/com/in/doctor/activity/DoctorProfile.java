package com.in.doctor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.in.doctor.R;
import com.in.doctor.adapter.DoctorTimePriceAdapter;
import com.in.doctor.adapter.DoctorUploadedImageAdapter;
import com.in.doctor.adapter.MyReviewAdapter;
import com.in.doctor.model.CareAndCheckupModel;
import com.in.doctor.model.DoctorTimePrice;
import com.in.doctor.model.MyReviewModel;

import java.util.ArrayList;
import java.util.List;

public class DoctorProfile extends AppCompatActivity {


    RecyclerView recyclerView, reviewRecycler, timeRecycler;
    DoctorUploadedImageAdapter adapter;
    List<CareAndCheckupModel> list = new ArrayList<>();

    MyReviewAdapter reviewAdapter;
    List<MyReviewModel> reviewList = new ArrayList<>();

    DoctorTimePriceAdapter doctorTimePriceAdapter;
    List<DoctorTimePrice> timePriceList = new ArrayList<>();

    Button bookAppointment;
    ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        getSupportActionBar().hide();
        init();
        imageData();
        reviewData();
        timePriceData();
    }

    public void init() {
        recyclerView = findViewById(R.id.recycler);
        reviewRecycler = findViewById(R.id.reviewRecycler);
        timeRecycler = findViewById(R.id.timeRecycler);
        bookAppointment = findViewById(R.id.BookAppointment);
        backButton = findViewById(R.id.backButton);


        bookAppointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BookAppointment.class);
                startActivity(intent);
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                intent.putExtra("s", 1);
                startActivity(intent);
            }
        });

    }


    public void imageData() {

        CareAndCheckupModel model = new CareAndCheckupModel("", "");
        CareAndCheckupModel model1 = new CareAndCheckupModel("", "");
        CareAndCheckupModel model2 = new CareAndCheckupModel("", "");
        CareAndCheckupModel model3 = new CareAndCheckupModel("", "");
        list.add(model);
        list.add(model1);
        list.add(model2);
        list.add(model3);


        adapter = new DoctorUploadedImageAdapter(list, getApplicationContext(), new DoctorUploadedImageAdapter.Click() {
            @Override
            public void onClick(int position) {

            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    public void reviewData() {




        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        reviewRecycler.setLayoutManager(mLayoutManager);
        reviewRecycler.setAdapter(reviewAdapter);
    }

    public void timePriceData() {


        DoctorTimePrice model = new DoctorTimePrice("9:00 - 9:30", "$259");
        timePriceList.add(model);
        timePriceList.add(model);
        timePriceList.add(model);
        timePriceList.add(model);
        timePriceList.add(model);
        timePriceList.add(model);
        timePriceList.add(model);


        doctorTimePriceAdapter = new DoctorTimePriceAdapter(timePriceList, this, new DoctorTimePriceAdapter.Click() {
            @Override
            public void itemClick(int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        timeRecycler.setLayoutManager(mLayoutManager);
        timeRecycler.setAdapter(doctorTimePriceAdapter);
    }


}