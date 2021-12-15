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
import com.in.doctor.adapter.OnlineConsultantAdapter;
import com.in.doctor.model.OnlineConsultantModel;

import java.util.ArrayList;
import java.util.List;

public class OnlineConsultants extends AppCompatActivity {

    ImageView nevBack;
    TextView headerTitle;

    OnlineConsultantAdapter adapter;
    RecyclerView recyclerView;
    List<OnlineConsultantModel> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_consultants);
        getSupportActionBar().hide();

        init();
        recyclerData();
    }

    public void init() {

        nevBack = findViewById(R.id.nevBack);
        headerTitle = findViewById(R.id.header_title);
        recyclerView = findViewById(R.id.recycler);

        headerTitle.setText("Online Consultant");


        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Home.class);
                startActivity(intent);
            }
        });

    }

    public void recyclerData() {


        OnlineConsultantModel model = new OnlineConsultantModel("9956328", "Lorem ipsum.", "Gujarat ", "$199", "", "Pending");
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);

        adapter = new OnlineConsultantAdapter(list, this, new OnlineConsultantAdapter.Click() {
            @Override
            public void onButtonClick(int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}