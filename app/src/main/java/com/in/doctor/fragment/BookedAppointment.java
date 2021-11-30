package com.in.doctor.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.in.doctor.R;
import com.in.doctor.activity.Home;
import com.in.doctor.adapter.BookedAppointmentAdapter;
import com.in.doctor.adapter.ManageCalenderAdapter;
import com.in.doctor.model.BookedAppointmentModel;
import com.in.doctor.model.ManageCalendarModel;

import java.util.ArrayList;
import java.util.List;


public class BookedAppointment extends Fragment {

    ImageView nevBack;
    TextView headerTitle;
    RecyclerView recyclerView;
    BookedAppointmentAdapter adapter;
    List<BookedAppointmentModel> list = new ArrayList<>();

    View view;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_booked_appoinment, container, false);
        init();
        recyclerData();

        return view;
    }

    public void init() {
        nevBack = view.findViewById(R.id.nevBack);
        headerTitle = view.findViewById(R.id.header_title);
        recyclerView = view.findViewById(R.id.recycler);

        headerTitle.setText("My Booked Appointment");

        nevBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Home.class);
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

        adapter = new BookedAppointmentAdapter(list, getContext(), new BookedAppointmentAdapter.Click() {
            @Override
            public void onButtonClick(int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
    }

}