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
import com.in.doctor.adapter.ManageCalenderAdapter;
import com.in.doctor.model.ManageCalendarModel;

import java.util.ArrayList;
import java.util.List;

public class ManageCalendar extends AppCompatActivity {

    ImageView nevBack;
    TextView headerTitle;


    ManageCalenderAdapter adapter;
    RecyclerView recyclerView;
    List<ManageCalendarModel> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_calendar);
        getSupportActionBar().hide();

        init();
        recyclerData();
    }

    public void init() {

        nevBack = findViewById(R.id.nevBack);
        headerTitle = findViewById(R.id.header_title);
        recyclerView = findViewById(R.id.recycler);

        headerTitle.setText("Manage Calendar");


        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

    }

    public void recyclerData() {

        ManageCalendarModel model = new ManageCalendarModel("9956328", "Lorem ipsum.", "Gujarat ", "$199", "", "Pending");
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);

        adapter = new ManageCalenderAdapter(list, this, new ManageCalenderAdapter.Click() {
            @Override
            public void onButtonClick(int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}