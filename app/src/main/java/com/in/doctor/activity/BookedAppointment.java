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
import com.in.doctor.adapter.BookedAppointmentAdapter;
import com.in.doctor.model.BookedAppointmentModel;

import java.util.ArrayList;
import java.util.List;

public class BookedAppointment extends AppCompatActivity {

    ImageView nevBack;
    TextView headerTitle;
    RecyclerView recyclerView;
    BookedAppointmentAdapter adapter;
    List<BookedAppointmentModel> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booked_appointment);
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


        BookedAppointmentModel model = new BookedAppointmentModel("9956328", "Lorem ipsum.", "Gujarat ", "$199", "");
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);
        list.add(model);

        adapter = new BookedAppointmentAdapter(list, this, new BookedAppointmentAdapter.Click() {
            @Override
            public void onButtonClick(int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }
}