package com.in.doctor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.in.doctor.R;
import com.in.doctor.adapter.ManageBookingAdapter;
import com.in.doctor.model.ManageBookingModel;

import java.util.ArrayList;
import java.util.List;

public class Request extends AppCompatActivity {
    ImageView nevBack;
    TextView headerTitle;
    RecyclerView recyclerView;
    ManageBookingAdapter adapter;
    List<ManageBookingModel> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        getSupportActionBar().hide();
        init();
        recyclerData();
    }


    public void init() {
        nevBack = findViewById(R.id.nevBack);
        headerTitle = findViewById(R.id.header_title);
        recyclerView = findViewById(R.id.recycler);

        headerTitle.setText("My Booked Appointment");

        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });
    }

    public void recyclerData() {


        ManageBookingModel model = new ManageBookingModel("008979977", "27/09/2021", "video consult", "02:30");
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);

        adapter = new ManageBookingAdapter(list, this, new ManageBookingAdapter.Click() {
            @Override
            public void onClickAccept(int position) {

            }

            @Override
            public void onClickCancel(int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}